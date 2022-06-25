package com.hindsight.user_api.exception

import com.hindsight.core.exception.GlobalException
import com.hindsight.core.exception.IGlobalMessage
import org.springframework.http.HttpStatus

class UserException(userMessage: IGlobalMessage): GlobalException(userMessage)

enum class UserExceptionMessage(
    override val status: HttpStatus,
    override val message: String
): IGlobalMessage {

    CANNOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저 없음"),
    LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, "아이디 중복"),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "로그인 실패"),
    AUTH_FAIL(HttpStatus.BAD_REQUEST, "인증 실패")
    ;

    override fun getName(): String = this.name
}