package com.univesp.game.mapper

import com.univesp.game.dto.NewRentForm
import com.univesp.game.model.Rent
import com.univesp.game.service.CustomerService
import com.univesp.game.service.GameService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class NewRentFormMapper(
    val gameService: GameService,
    val customerService: CustomerService
) : Mapper<NewRentForm, Rent> {
    override fun map(t: NewRentForm): Rent {
        val game = gameService.searchById(t.gameId)

        return Rent(
            customer = customerService.searchById(t.customerId),
            game = game,
            rentDate = LocalDate.now(),
            daysRented = t.daysRented,
            originalPrice = t.daysRented * game.pricePerDay,
            note = t.note
        )
    }
}