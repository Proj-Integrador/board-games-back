package com.univesp.game.controller

import com.univesp.game.model.Category
import com.univesp.game.service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
@CrossOrigin
class CategoryController(
    val service: CategoryService
) {

    @Operation(summary = "Get the list of all Categories")
    @GetMapping
    fun listAll(): List<Category> {
        return service.listAll()
    }

    @Operation(summary = "Get a Category by its id")
    @GetMapping("/{id}")
    fun searchById(
        @PathVariable id: Long
    ): Category {
        return service.searchById(id)
    }

    @Operation(summary = "Create a Category")
    @PostMapping
    fun register(
        @RequestBody @Valid category: Category
    ): ResponseEntity<Category> {
        var newCategory = service.register(category)
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory)
    }
}