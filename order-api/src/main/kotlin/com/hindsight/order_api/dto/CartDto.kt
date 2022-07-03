package com.hindsight.order_api.dto

class CartDto {

    class Request{
        data class Add(
            val userId: Long,
            val itemId: Long
        )
    }

    class Response{
    }
}