package com.hindsight.merchandies_api.domain.category

import com.hindsight.merchandies_api.entity.CategoryEntity

class Category {

    companion object {
        fun fromEntity(category: CategoryEntity) = Response(category.id, category.name)
    }

    class Request {
        data class Add(
            val code: String,
            val name: String
        )
    }

    data class Response(
        val id: Long,
        val name: String

    )

}
