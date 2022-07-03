package com.hindsight.order_api.repository

import com.hindsight.order_api.domain.CartEntity
import com.hindsight.order_api.domain.ItemInCartEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ItemInCartRepository : JpaRepository<ItemInCartEntity, Long> {
    fun findByCartAndItemId(cart: CartEntity, itemId: Long): Optional<ItemInCartEntity>
}