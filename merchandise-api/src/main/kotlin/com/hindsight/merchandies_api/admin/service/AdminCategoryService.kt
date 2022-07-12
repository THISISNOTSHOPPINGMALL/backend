package com.hindsight.merchandies_api.admin.service

import com.hindsight.merchandies_api.domain.category.Category
import com.hindsight.merchandies_api.entity.CategoryEntity
import com.hindsight.merchandies_api.CategoryRepository
import org.springframework.stereotype.Service

@Service
class AdminCategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun createCategory(request: Category.Request.Add): Category.Response {
        // TODO : Admin 용 ErrorCode 정의
        categoryRepository.findByCode(request.code)?.let { throw Exception("Duplicated category code") }
        return Category.fromEntity(
            categoryRepository.save(CategoryEntity.of(request.code, request.name))
        )
    }
}
