package com.hindsight.user_api.router

import com.hindsight.user_api.user.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Router(private val userHandler: UserHandler) {

    @Bean
    fun userRouter() = coRouter {
        "".nest{
            POST("", accept(MediaType.APPLICATION_JSON), userHandler::addUser)
            POST("/loginId", accept(MediaType.APPLICATION_JSON), userHandler::checkDuplicatedUserLoginId)
        }
    }

}