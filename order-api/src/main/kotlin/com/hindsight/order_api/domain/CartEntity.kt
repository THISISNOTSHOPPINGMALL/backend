package com.hindsight.order_api.domain

import java.time.LocalDateTime
import java.time.ZoneId
import javax.persistence.*

@Entity
@Table(name = "cart")
class CartEntity private constructor(
    userId: Long
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null


    var userId: Long = userId
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
    var updatedAt: LocalDateTime? = null
    var deletedAt: LocalDateTime? = null

    companion object {
        fun of(
            userId: Long
        ): CartEntity = CartEntity(
            userId = userId
        )
    }

}
