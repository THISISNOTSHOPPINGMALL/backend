package com.hindsight.merchandies_api.entity

import com.hindsight.merchandies_api.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity private constructor(
    code: String,
    name: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long = 0

    @Column(nullable = false)
    var code: String = code

    @Column(nullable = false)
    var name: String = name

    companion object {
        fun of(code: String, name: String): CategoryEntity = CategoryEntity(code, name)
    }
}
