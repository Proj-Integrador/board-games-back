package com.univesp.game.mapper

import com.univesp.game.factory.NewGameFormFactory
import com.univesp.game.model.Category
import com.univesp.game.service.CategoryService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class NewGameFormMapperTest {

    @Mock
    lateinit var categoryService: CategoryService

    @InjectMocks
    private lateinit var newGameFormMapper: NewGameFormMapper

    @Test
    fun map() {
        val expected = NewGameFormFactory().create()

        val category = Category(30L, "cool category name")

        `when`(categoryService.searchById(30L)).thenReturn(category)

        val result = newGameFormMapper.map(expected)

        assertEquals(expected.name, result.name)
        assertEquals(expected.image, result.image)
        assertEquals(expected.stockTotal, result.stockTotal)
        assertEquals(expected.categoryId, result.category.id)
        assertEquals(category, result.category)
        assertEquals(expected.pricePerDay, result.pricePerDay)
        assertEquals(expected.ludopediaLink, result.ludopediaLink)
    }
}