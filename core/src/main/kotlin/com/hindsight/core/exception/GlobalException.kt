package com.hindsight.core.exception

import org.springframework.http.HttpStatus

class GlobalException(val globalMessage: GlobalMessage): RuntimeException()

enum class GlobalMessage(
    val status: HttpStatus,
    val message: String
){
    // Channel
    TEST_EXCEPTION(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다."),

    ;
}