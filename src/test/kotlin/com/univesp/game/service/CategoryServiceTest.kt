package com.univesp.game.service

import com.univesp.game.exception.NotFoundException
import com.univesp.game.exception.RecordAlreadyExistsException
import com.univesp.game.factory.CategoryFactory
import com.univesp.game.repository.CategoryRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CategoryServiceTest {

    @Mock
    lateinit var categoryRepository: CategoryRepository

    @InjectMocks
    lateinit var categoryService: CategoryService

    private val categoryMock = CategoryFactory().create()

    @Test
    fun listAll() {
        `when`(categoryRepository.findAll()).thenReturn(listOf(categoryMock))

        val result = categoryService.listAll()
        assertEquals(listOf(categoryMock), result)
    }

    @Test
    fun `when get category by id Then return category`() {
        `when`(categoryRepository.findById(any())).thenReturn(Optional.of(categoryMock))
        val result = categoryService.searchById(22L)
        assertEquals(categoryMock, result)
    }

    @Test
    fun `when get category by id not found Then Throw exception`() {
        `when`(categoryRepository.findById(any())).thenThrow(NotFoundException("Categoria não encontrada."))

        Assertions.assertThrows(NotFoundException::class.java) {
            categoryService.searchById(22L)
        }
    }

    @Test
    fun searchByName() {
        `when`(categoryRepository.findByNameIgnoreCase("cool category name")).thenReturn(listOf(categoryMock))
        var result = categoryService.searchByName("cool category name")
        assertEquals(listOf(categoryMock), result)
    }

    @Test
    fun `When category already exists, throw exception`() {
        `when`(categoryService.searchByName("cool category name")).thenReturn(listOf(categoryMock))

        Assertions.assertThrows(RecordAlreadyExistsException::class.java) {
            categoryService.register(categoryMock)
        }
    }

    @Test
    fun `When register category, return category`() {
        `when`(categoryService.searchByName("cool category name")).thenReturn(listOf())
        `when`(categoryRepository.save(categoryMock)).thenReturn(categoryMock)

        val result = categoryService.register(categoryMock)
        assertEquals(categoryMock, result)
    }
}