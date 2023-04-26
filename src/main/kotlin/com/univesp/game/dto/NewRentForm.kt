package com.univesp.game.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class NewRentForm (
    @field:NotNull(message = "A identificação do cliente é obrigatória.")
    val customerId: Long,

    @field:NotNull(message = "A identificação do jogo é obrigatória.")
    val gameId: Long,

    @field:NotNull(message = "A quantidade de dias de aluguel é obrigatória..")
    @field:Min(1)
    val daysRented: Int,

    val note: String? = null
)
