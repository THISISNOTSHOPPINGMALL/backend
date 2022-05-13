package com.hindsight.user_api.user

import com.hindsight.user_api.exception.UserException
import com.hindsight.user_api.exception.UserExceptionMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait

@Service
class UserService(private val userRepository: UserRepository, val operator: TransactionalOperator) {

    suspend fun addUser(loginId: String, pw: String, address: String, phoneNumber: String) =
        operator.executeAndAwait {
            checkIdIsDuplicate(loginId)

            userRepository.save(User.of(loginId = loginId, pw = pw, address = address, phoneNumber = phoneNumber))
            Unit
        }

    suspend fun checkIdIsDuplicate(id: String) =
        userRepository.findByLoginId(id)
            ?.let { throw UserException(UserExceptionMessage.LOGIN_ID_DUPLICATED) }
            ?: Unit

//    suspend fun login(loginId: String, pw: String) =
//        userRepository.findByLoginIdAndPw(loginId = loginId, pw = encryptPw(pw))
//            ?: run {
//                operator.executeAndAwait {
//                    userRepository.findByLoginId(loginId)
//                        ?.let { failedUser -> userRepository.save(failedUser.copy(
//
//                        ))}
//                }
//                throw UserException(UserExceptionMessage.LOGIN_FAIL)
//            }

}