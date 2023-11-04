package com.univesp.game.mapper

import com.univesp.game.factory.RentFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RentViewMapperTest {

    @Test
    fun map() {
        val rent = RentFactory().create()
        val mapper = RentViewMapper()
        val result = mapper.map(rent)

        assertEquals(rent.id, result.id)
        assertEquals(rent.customer.id, result.customerId)
        assertEquals(rent.game.id, result.gameId)
        assertEquals(rent.rentDate, result.rentDate)
        assertEquals(rent.daysRented, result.daysRented)
        assertEquals(rent.returnDate, result.returnDate)
        assertEquals(rent.originalPrice, result.originalPrice)
        assertEquals(rent.delayFee, result.delayFee)
        assertEquals(rent.note, result.note)
        assertEquals(rent.customer.id, result.customer.id)
        assertEquals(rent.customer.name, result.customer.name)
        assertEquals(rent.game.id, result.game.id)
        assertEquals(rent.game.name, result.game.name)
        assertEquals(rent.game.category.id, result.game.categoryId)
        assertEquals(rent.game.category.name, result.game.categoryName)
        assertEquals(rent.game.ludopediaLink, result.game.ludopediaLink)
    }
}