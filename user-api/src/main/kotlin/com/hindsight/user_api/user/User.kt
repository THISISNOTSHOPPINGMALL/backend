package com.hindsight.user_api.user

import com.hindsight.core.validation.isAddress
import com.hindsight.core.validation.isPhoneNumber
import com.hindsight.user_api.validation.isUserId
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.crypto.bcrypt.BCrypt
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Table("user")
data class User(
    @Id
    val id: Long? = null,
    val loginId: String,
    val uniqueValue: String,
    val pw: String,
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul")),
    val lastLoginAt: LocalDateTime? = null,
    val token: String? = null,
    val address: String,
    val phoneNumber: String
) {
    companion object {
        fun of(loginId: String, pw: String, address: String, phoneNumber: String) =
            User(
                loginId = loginId,
                uniqueValue = UUID.randomUUID().toString(),
                pw = encryptPw(pw),
                address = address,
                phoneNumber = phoneNumber
            )
    }

    fun isInfoValid(): Boolean = address.isAddress() && phoneNumber.isPhoneNumber() && loginId.isUserId()
}


fun encryptPw(pw: String): String = BCrypt.gensalt().let { salt -> BCrypt.hashpw(pw, salt) }

// Request
data class LoginIdCheckRequest(
    val loginId: String
)

fun LoginIdCheckRequest.isValid(): Boolean = loginId.isUserId()

// Response