package com.hindsight.core.exception

import org.springframework.http.HttpStatus

interface IGlobalMessage {
    val status: HttpStatus
    val message: String

    fun getName(): String
}

open class GlobalException(val globalMessage: IGlobalMessage) : RuntimeException()

enum class GlobalMessage(
    override val status: HttpStatus,
    override val message: String
) : IGlobalMessage {

    TEST_EXCEPTION(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알수없는 에러가 발생했습니다."),
    REQUEST_NOT_VALID(HttpStatus.CONFLICT, "요청이 잘못되었습니다.")

    ;

    override fun getName(): String = this.name

}