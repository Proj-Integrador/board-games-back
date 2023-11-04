package com.univesp.game.factory

import com.univesp.game.dto.RentCustomerView
import com.univesp.game.dto.RentGameView
import com.univesp.game.dto.RentView
import java.time.LocalDate

class RentViewFactory {
    fun create(): RentView {
        val game = GameFactory().create()
        val customer = CustomerFactory().create()
        return RentView(
            id = 3L,
            customerId = CustomerFactory().create().id!!,
            gameId = GameFactory().create().id!!,
            rentDate = LocalDate.parse("2023-01-01"),
            daysRented = 10,
            returnDate = LocalDate.parse("2023-01-01").plusDays(11L),
            originalPrice = 2000,
            delayFee = 50,
            note = "cool note",
            customer = RentCustomerView(customer.id!!, customer.name),
            game = RentGameView(
                game.id!!,
                game.name,
                game.category.id,
                game.category.name,
                game.ludopediaLink
            )
        )
    }
}