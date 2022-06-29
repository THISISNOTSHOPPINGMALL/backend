package com.hindsight.merchandies_api.domain

import com.hindsight.merchandies_api.domain.base.BaseEntity
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
@Table(name = "item")
class ItemEntity private constructor(
    name: String,
    stock: Int,
    category: CategoryEntity,
    mall: MallEntity
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    val id: Long = 0

    @Column(nullable = false)
    var name: String = name

    @Column(nullable = false)
    var stock: Int = stock

    var deletedAt: LocalDateTime? = null

    @OneToMany(mappedBy = "item", cascade = [CascadeType.PERSIST], targetEntity = ReviewEntity::class)
    var reviews: List<ReviewEntity> = emptyList()

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    var category: CategoryEntity = category

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mall_id")
    var mall: MallEntity = mall

    companion object {
        fun of(
            name: String,
            stock: Int,
            categoryEntity: CategoryEntity,
            mallEntity: MallEntity
        ): ItemEntity = ItemEntity(
            name = name,
            stock = stock,
            category = categoryEntity,
            mall = mallEntity
        )
    }

}
