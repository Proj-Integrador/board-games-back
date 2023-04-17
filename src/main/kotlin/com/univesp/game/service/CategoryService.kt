package com.univesp.game.service

import com.univesp.game.exception.NotFoundException
import com.univesp.game.exception.RecordAlreadyExistsException
import com.univesp.game.model.Category
import com.univesp.game.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val repository: CategoryRepository
) {

    fun listAll(): List<Category> {
        return repository.findAll()
    }

    fun searchById(id: Long): Category {
        return repository.findById(id)
            .orElseThrow{NotFoundException("Categoria não encontrada.")}
    }

    fun searchByName(name: String): List<Category> {
        return repository.findByNameIgnoreCase(name)
    }

    fun register(category: Category): Category {
        val categories = searchByName(category.name)
        if (categories.isNotEmpty()){
            throw RecordAlreadyExistsException("Já existe uma categoria cadastrada com este nome.")
        }
        return repository.save(category)
    }
}