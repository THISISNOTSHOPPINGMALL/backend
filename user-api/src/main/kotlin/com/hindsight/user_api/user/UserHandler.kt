package com.hindsight.user_api.user

import com.hindsight.core.exception.GlobalException
import com.hindsight.core.exception.GlobalMessage
import com.hindsight.core.response.BaseResponse
import com.hindsight.user_api.exception.UserExceptionMessage
import com.hindsight.user_api.user.dto.LoginIdCheckRequest
import com.hindsight.user_api.user.dto.UserAddRequest
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok
import java.util.*


private val logger = KotlinLogging.logger {}

@Component
class UserHandler(private val userService: UserService) {

    suspend fun addUser(request: ServerRequest): ServerResponse {
        val req: UserAddRequest = request.awaitBody()

        if (!req.isValid()) {
            throw GlobalException(GlobalMessage.REQUEST_NOT_VALID)
        }

        userService.addUser(loginId = req.loginId, pw = req.pw, address = req.address, phoneNumber = req.phoneNumber)

        return ok().buildAndAwait()
    }

    suspend fun findByUniqueValue(request: ServerRequest): ServerResponse {
        val uv: String = request.pathVariable("uv")

        return userService.findByUniqueValue(uniqueValue = uv).let{
            ok().bodyValueAndAwait(BaseResponse(it))
        }
    }

    suspend fun checkDuplicatedUserLoginId(request: ServerRequest): ServerResponse {
        val req: LoginIdCheckRequest = request.awaitBody()

        if (!req.isValid()) {
            logger.error { req }
            throw GlobalException(GlobalMessage.REQUEST_NOT_VALID)
        }

        userService.checkIdIsDuplicate(req.loginId)

        return ok().buildAndAwait()
    }

    suspend fun login(request: ServerRequest): ServerResponse {
        val (loginId, pw) = request.headers().header("Authorization").first()
            .let { rawStr ->
                val (key, info) = rawStr.split(" ")
                if (key == "Bearer") {
                    Base64.getDecoder().decode(info).let { String(it) }.split(":")
                } else {
                    throw GlobalException(UserExceptionMessage.LOGIN_FAIL)
                }
            }


        return userService.login(loginId = loginId, pw = pw)
            .let {
                ok().bodyValueAndAwait(
                    BaseResponse(it)
                )
            }
    }

    suspend fun authByToken(request: ServerRequest): ServerResponse {
        val token = request.headers().header("x-auth-shop-token").first()

        userService.authByToken(token)
        return ok().buildAndAwait()
    }

    suspend fun logout(request: ServerRequest): ServerResponse {
        val token = request.headers().header("x-auth-shop-token").first()

        userService.logout(token)
        return ok().buildAndAwait()
    }

}
