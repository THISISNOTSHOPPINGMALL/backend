package com.hindsight.user_api.user

import com.hindsight.core.exception.GlobalException
import com.hindsight.user_api.exception.UserException
import com.hindsight.user_api.exception.UserExceptionMessage
import com.hindsight.user_api.user.dto.UserDetailResponse
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


const val TOKEN_TIME_OUT = 30L // minutes

@Service
class UserService(private val userRepository: UserRepository, val operator: TransactionalOperator) {

    suspend fun addUser(loginId: String, pw: String, address: String, phoneNumber: String) =
        operator.executeAndAwait {
            checkIdIsDuplicate(loginId)

            userRepository.save(
                User.of(
                    loginId = loginId,
                    pw = pw,
                    address = address,
                    phoneNumber = phoneNumber
                )
            ).id
        }

    suspend fun findByUniqueValue(uniqueValue: String): UserDetailResponse =
        userRepository.findByUniqueValue(uniqueValue)
            ?.let { UserDetailResponse.from(it) }
            ?: throw GlobalException(UserExceptionMessage.CANNOT_FOUND_USER)


    suspend fun checkIdIsDuplicate(id: String) =
        userRepository.findByLoginId(id)
            ?.let { throw UserException(UserExceptionMessage.LOGIN_ID_DUPLICATED) }
            ?: Unit

    suspend fun login(loginId: String, pw: String): UserDetailResponse =
        userRepository.findByLoginId(loginId = loginId)
            ?.let { user ->
                if (BCrypt.checkpw(pw, user.pw) && user.failedLoginCount < 5) {
                    userRepository.save(
                        user.copy(
                            failedLoginCount = 0,
                            lastLoginAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")),
                            token = UUID.randomUUID().toString()
                        )
                    ).let { UserDetailResponse.from(it) }
                } else {
                    userRepository.save(
                        user.copy(
                            failedLoginCount = user.failedLoginCount + 1
                        )
                    )
                    throw UserException(UserExceptionMessage.LOGIN_FAIL)
                }
            } ?: throw UserException(UserExceptionMessage.LOGIN_FAIL)

    /*
        TOKEN_TIME_OUT = 30분
     */
    suspend fun authByToken(token: String) =
        userRepository.findByToken(token = token)?.let {
            if (it.lastLoginAt?.plusMinutes(TOKEN_TIME_OUT)?.isAfter(LocalDateTime.now()) == true) {
                userRepository.save(it.copy(lastLoginAt = LocalDateTime.now()))
            } else {
                throw UserException(UserExceptionMessage.LOGIN_FAIL)
            }
        } ?: throw UserException(UserExceptionMessage.LOGIN_FAIL)

    suspend fun logout(token: String) =
        userRepository.findByToken(token = token)?.let {
            userRepository.save(it.copy(token = null))
        } ?: throw UserException(UserExceptionMessage.LOGIN_FAIL)


}