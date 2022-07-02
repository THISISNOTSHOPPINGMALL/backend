package com.hindsight.order_api.dto

class CartDto {

    class Request{
        data class Add(
            val userId: Long,
            val itemId: Long,
            val amount: Int
        )
    }

    class Response{
    }
}