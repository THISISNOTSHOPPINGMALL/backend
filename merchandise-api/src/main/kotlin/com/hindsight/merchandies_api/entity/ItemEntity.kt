package com.hindsight.merchandies_api.entity

import com.hindsight.merchandies_api.entity.base.BaseEntity
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "item")
class ItemEntity private constructor(
    name: String,
    stock: Int,
    category: CategoryEntity,
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

    companion object {
        fun of(
            name: String,
            stock: Int,
            categoryEntity: CategoryEntity,
        ): ItemEntity = ItemEntity(
            name = name,
            stock = stock,
            category = categoryEntity
        )
    }

}
