package com.univesp.game.model

import jakarta.persistence.*

@Entity(name = "games")
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,
    val image: String? = null,
    var stockTotal: Int,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    val pricePerDay: Int,
    val ludopediaLink: String? = null
)
