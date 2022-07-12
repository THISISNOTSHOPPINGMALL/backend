package com.hindsight.merchandies_api

import com.hindsight.merchandies_api.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long> {

    fun findByCode(code: String): CategoryEntity?
}
