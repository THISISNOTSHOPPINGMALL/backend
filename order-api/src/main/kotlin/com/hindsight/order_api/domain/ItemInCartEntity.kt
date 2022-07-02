package com.hindsight.order_api.domain

import java.time.LocalDateTime
import java.time.ZoneId
import javax.persistence.*
import javax.persistence.FetchType.LAZY


@Entity
@Table(name = "item_in_cart")
class ItemInCartEntity private constructor(
    cart: CartEntity,
    itemId: Long,
    amount: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    val cart: CartEntity = cart
    val itemId: Long = itemId
    var amount: Int = amount

    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
    var updatedAt: LocalDateTime? = null
    var deletedAt: LocalDateTime? = null

    companion object {
        fun of(
            cart: CartEntity,
            itemId: Long,
            amount: Int
        ): ItemInCartEntity = ItemInCartEntity(
            cart = cart,
            itemId = itemId,
            amount = amount
        )
    }

}
