package com.univesp.game.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "rentals")
data class Rent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game,

    @Column(name = "rent_date")
    val rentDate: LocalDate,

    val daysRented: Int? = null,
    var returnDate: LocalDate? = null,
    val originalPrice: Int,
    var delayFee: Int? = null,
    val note: String? = null
)
