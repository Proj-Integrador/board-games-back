package com.univesp.game.dto

data class GameView(
    val id: Long? = null,
    val name: String,
    val image: String? = null,
    val stockTotal: Int,
    val categoryId: Long,
    val pricePerDay: Int,
    val ludopediaLink: String? = null,
    val categoryName: String
)