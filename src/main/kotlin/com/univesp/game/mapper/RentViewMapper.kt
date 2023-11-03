package com.univesp.game.mapper

import com.univesp.game.dto.RentCustomerView
import com.univesp.game.dto.RentGameView
import com.univesp.game.dto.RentView
import com.univesp.game.model.Rent
import org.springframework.stereotype.Component

@Component
class RentViewMapper : Mapper<Rent, RentView> {
    override fun map(t: Rent): RentView {
        return RentView(
            id = t.id!!,
            customerId = t.customer.id!!,
            gameId = t.game.id,
            rentDate = t.rentDate,
            daysRented = t.daysRented,
            returnDate = t.returnDate,
            originalPrice = t.originalPrice,
            delayFee = t.delayFee,
            note = t.note,
            customer = RentCustomerView(
                id = t.customer.id,
                name = t.customer.name
            ),
            game = RentGameView(
                id = t.game.id,
                name = t.game.name,
                categoryId = t.game.category.id,
                categoryName = t.game.category.name,
                ludopediaLink = t.game.ludopediaLink
            )
        )
    }
}
