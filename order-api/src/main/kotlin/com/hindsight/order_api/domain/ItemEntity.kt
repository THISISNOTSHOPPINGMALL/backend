package com.hindsight.order_api.domain

import javax.persistence.*

@Entity
@Table(name = "item")
class ItemEntity private constructor(
    title: String,
    text: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    var id: Long? = null


    var title: String = title
    var text: String = text
    var deleted: Boolean = false

    companion object {
        fun of(
            title: String,
            text: String
        ): ItemEntity = ItemEntity(
            title = title,
            text = text
        )
    }

}
