package com.univesp.game.mapper

import com.univesp.game.factory.GameFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameViewMapperTest {

    @Test
    fun map() {
        var expected = GameFactory().create()

        var result = GameViewMapper().map(expected)

        assertEquals(expected.id, result.id)
        assertEquals(expected.name, result.name)
        assertEquals(expected.image, result.image)
        assertEquals(expected.stockTotal, result.stockTotal)
        assertEquals(expected.category.id, result.categoryId)
        assertEquals(expected.pricePerDay, result.pricePerDay)
        assertEquals(expected.ludopediaLink, result.ludopediaLink)
        assertEquals(expected.category.name, result.categoryName)
    }
}