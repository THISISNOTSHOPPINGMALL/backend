package com.hindsight.order_api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WebClientConfig(
    @Value("\${endpoint.merchandise}") private val merchandiseUrl: String
) {

    @Bean
    fun merchandiseWebClient(): WebClient =
        WebClient
            .builder()
            .baseUrl(merchandiseUrl)
            .build()

}