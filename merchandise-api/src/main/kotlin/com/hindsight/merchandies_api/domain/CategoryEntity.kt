package com.hindsight.merchandies_api.domain

import com.hindsight.merchandies_api.domain.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity private constructor(
    name: String,
    level: Long
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long = 0

    @Column(nullable = false)
    var name: String = name

    @Column(nullable = false)
    var level: Long = level

    @OneToMany(mappedBy = "category", cascade = [CascadeType.PERSIST], targetEntity = ItemEntity::class)
    var items: List<ItemEntity> = emptyList()

}
