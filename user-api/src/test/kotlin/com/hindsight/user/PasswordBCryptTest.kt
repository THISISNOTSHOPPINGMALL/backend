package com.hindsight.user

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.security.crypto.bcrypt.BCrypt

class PasswordBCryptTest: StringSpec() {

    init{
        "Hashing 된 값은 Salt 로 시작한다."{
            val rawPassword = "rawPw1234"
            val salt = BCrypt.gensalt()

            val hashedPw = BCrypt.hashpw(rawPassword, salt)

            hashedPw.startsWith(salt) shouldBe true
        }

        "저장된 Salt 없이 비밀번호 검증 가능: 비밀번호 일치"{
            val rawPassword = "rawPw1234"
            val salt = BCrypt.gensalt()

            val hashedPassword = BCrypt.hashpw(rawPassword, salt)

            BCrypt.checkpw(rawPassword, hashedPassword) shouldBe true
        }

        "저장된 Salt 없이 비밀번호 검증 가능: 비밀번호 불일치"{
            val rawPassword = "rawPw1234"
            val wrongPassword = "rawPw123"
            val salt = BCrypt.gensalt()

            val hashedPassword = BCrypt.hashpw(rawPassword, salt)

            BCrypt.checkpw(wrongPassword, hashedPassword) shouldBe false
        }

    }
}