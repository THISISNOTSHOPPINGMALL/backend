package com.hindsight.user_api.user

import com.hindsight.core.exception.GlobalException
import com.hindsight.core.exception.GlobalMessage
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.buildAndAwait


private val logger = KotlinLogging.logger {}

@Component
class UserHandler(private val userService: UserService) {

    suspend fun addUser(request: ServerRequest): ServerResponse {
        val req: User = request.awaitBody()

        if (!req.isInfoValid()) {
            logger.error { req }
            throw GlobalException(GlobalMessage.REQUEST_NOT_VALID)
        }

        userService.addUser(loginId = req.loginId, pw = req.pw, address = req.address, phoneNumber = req.phoneNumber)

        return ok().buildAndAwait()
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

}
