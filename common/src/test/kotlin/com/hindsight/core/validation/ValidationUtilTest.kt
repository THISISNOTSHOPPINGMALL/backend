package com.hindsight.core.validation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ValidationUtilTest : StringSpec() {
    init {

        "전화번호 유효성 테스트" {
            val correctPhoneNumbers = listOf("82 010-0000-0000", "1792 010-1111-1111", )
            val wrongPhoneNumbers = listOf("", " ", "82 031-000-0000")

            correctPhoneNumbers.forEach { it.isPhoneNumber() shouldBe true }
            wrongPhoneNumbers.forEach { it.isPhoneNumber() shouldBe false }
        }

        "주소 유효성 테스트" {
            val correctAddresses = listOf("서울특별시 종로구 청와대로 1", "서울특별시 종로구 사직로 161", "경상북도 경주시 진현동 15-1")
            val wrongAddress = listOf(
                "",
                " ",
                "255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소255자이상의 도로명주소"
            )

            correctAddresses.forEach { it.isAddress() shouldBe true }
            wrongAddress.forEach { it.isAddress() shouldBe false }
        }

    }
}