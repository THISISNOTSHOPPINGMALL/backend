package com.hindsight.order_api.controller

import com.hindsight.core.response.BaseResponse
import com.hindsight.order_api.dto.CartDto
import com.hindsight.order_api.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI

@Controller
@RequestMapping("/cart")
class CartController(private val cartService: CartService) {

    @PostMapping("/{id}")
    suspend fun addItemToCart(
        @PathVariable id: Long,
        @RequestBody req: CartDto.Request.Add
    ): ResponseEntity<BaseResponse<Long>> =
        cartService.addItemToCart(cartId = id, req = req)
            .let {
                ResponseEntity
                    .created(URI.create("/cart/item/$it"))
                    .body(
                        BaseResponse(data = it)
                    )
            }

}