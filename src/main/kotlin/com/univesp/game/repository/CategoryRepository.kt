package com.univesp.game.repository

import com.univesp.game.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    fun findByNameIgnoreCase(name: String): List<Category>
}