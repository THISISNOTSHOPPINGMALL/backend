package com.hindsight.user_api.user

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
    val phoneNumber: String,
    val failedLoginCount: Long = 0
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

}

fun encryptPw(pw: String): String = BCrypt.gensalt().let { salt -> BCrypt.hashpw(pw, salt) }
