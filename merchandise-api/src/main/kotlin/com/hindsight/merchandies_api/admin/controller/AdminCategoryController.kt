package com.hindsight.merchandies_api.admin.controller

import com.hindsight.merchandies_api.domain.category.Category
import com.hindsight.merchandies_api.admin.service.AdminCategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/category")
class AdminCategoryController(
    private val categoryService: AdminCategoryService
) {

    @PostMapping
    fun createCategory(@RequestBody request: Category.Request.Add): ResponseEntity<Category.Response> {
        return ResponseEntity.ok(categoryService.createCategory(request))
    }

}
