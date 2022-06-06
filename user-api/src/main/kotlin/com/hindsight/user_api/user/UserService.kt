package com.hindsight.user_api.user

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

}