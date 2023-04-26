package com.univesp.game.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class NewGameForm(
    @field:NotBlank(message = "O nome é obrigatório.")
    val name: String,

    val image: String? = null,

    @field:NotNull
    @field:Min(1)
    val stockTotal: Int,

    @field:NotNull
    val categoryId: Long,

    @field:NotNull
    @field:Min(1)
    val pricePerDay: Int,

    val ludopediaLink: String? = null
)
