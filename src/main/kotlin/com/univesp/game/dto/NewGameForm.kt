package com.univesp.game.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class NewGameForm(
    @field:NotEmpty val name: String,
    val image: String? = null,
    @field:NotNull val stockTotal: Int,
    @field:NotNull val category: Int,
    @field:NotNull val pricePerDay: Int,
    val ludopediaLink: String? = null
)
