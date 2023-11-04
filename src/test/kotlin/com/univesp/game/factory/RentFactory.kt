package com.univesp.game.factory

import com.univesp.game.model.Rent
import java.time.LocalDate

class RentFactory {
    fun create(): Rent {
        return Rent(
            id = 3L,
            customer = CustomerFactory().create(),
            game = GameFactory().create(),
            rentDate = LocalDate.parse("2023-01-01"),
            daysRented = 10,
            returnDate = LocalDate.parse("2023-01-01").plusDays(11L),
            originalPrice = 2000,
            delayFee = 50,
            note = "cool note"
        )
    }
}