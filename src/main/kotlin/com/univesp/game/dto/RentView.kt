package com.univesp.game.dto

import java.time.LocalDate

data class RentView(
    val id: Long,
    val customerId: Long,
    val gameId: Long?,
    val rentDate: LocalDate,
    val daysRented: Int? = null,
    val returnDate: LocalDate? = null,
    val originalPrice: Int,
    val delayFee: Int? = null,
    val note: String? = null,
    val customer: RentCustomerView,
    val game: RentGameView
)
