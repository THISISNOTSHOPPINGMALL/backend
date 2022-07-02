package com.hindsight.order_api.api

import com.hindsight.core.exception.GlobalException
import com.hindsight.order_api.dto.ItemDto
import com.hindsight.order_api.exception.OrderMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@Component
class MerchandiseApi(
    private val merchandiseWebClient: WebClient
) {

    suspend fun getItemById(itemId: Long): ItemDto.Response.Simple =
        merchandiseWebClient
            .get()
            .uri {
                it.path("/item/{id}")
                    .build("id", itemId)
            }.awaitExchange {
                when (it.statusCode()) {
                    HttpStatus.OK -> it.awaitBody<ItemDto.Response.Simple>()
                    HttpStatus.NOT_FOUND -> throw GlobalException(OrderMessage.ITEM_NOT_FOUND)
                    else -> throw GlobalException(OrderMessage.ITEM_INTERNAL_ERROR)
                }
            }


}