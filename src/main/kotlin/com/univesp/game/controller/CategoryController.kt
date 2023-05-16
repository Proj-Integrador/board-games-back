package com.univesp.game.controller

import com.univesp.game.model.Category
import com.univesp.game.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
class CategoryController(
    val service: CategoryService
) {

    @GetMapping
    fun listAll(): List<Category> {
        return service.listAll()
    }

    @GetMapping("/{id}")
    fun searchById(
        @PathVariable id: Long
    ): Category {
        return service.searchById(id)
    }

    @PostMapping
    fun register(
        @RequestBody @Valid category: Category
    ): ResponseEntity<Category> {
        var newCategory = service.register(category)
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory)
    }
}