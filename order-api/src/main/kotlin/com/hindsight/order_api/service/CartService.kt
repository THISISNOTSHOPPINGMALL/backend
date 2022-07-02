package com.hindsight.order_api.service

import com.hindsight.core.exception.GlobalException
import com.hindsight.order_api.api.MerchandiseApi
import com.hindsight.order_api.domain.CartEntity
import com.hindsight.order_api.domain.ItemInCartEntity
import com.hindsight.order_api.dto.CartDto
import com.hindsight.order_api.dto.ItemDto
import com.hindsight.order_api.exception.OrderMessage
import com.hindsight.order_api.repository.CartRepository
import com.hindsight.order_api.repository.ItemInCartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CartService(
    private val cartRepository: CartRepository,
    private val itemInCartRepository: ItemInCartRepository,
    private val merchandiseApi: MerchandiseApi
) {

    @Transactional
    suspend fun addItemToCart(cartId: Long, req: CartDto.Request.Add): Long {

        val item: ItemDto.Response.Simple = merchandiseApi.getItemById(req.itemId)

        return withContext(Dispatchers.IO) {
            val cart: CartEntity = cartRepository.findById(cartId)
                .let {
                    if (it.isEmpty) throw GlobalException(OrderMessage.CART_NOT_FOUND) else it.get()
                }

            val itemInCart: ItemInCartEntity = itemInCartRepository.save(
                ItemInCartEntity.of(
                    cart = cart,
                    itemId = item.id,
                    amount = req.amount
                )
            )

            itemInCart.id ?: throw GlobalException(OrderMessage.CART_CREATE_INTERNAL_ERROR)
        }
    }
}