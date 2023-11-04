package com.univesp.game.factory

import com.univesp.game.dto.NewRentForm

class NewRentFormFactory {

    fun create(): NewRentForm {
        return NewRentForm(
            gameId = 1L,
            customerId = 2L,
            daysRented = 3,
            note = "Sample note"
        )
    }
}