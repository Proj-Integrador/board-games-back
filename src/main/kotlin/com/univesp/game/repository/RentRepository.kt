package com.univesp.game.repository

import com.univesp.game.model.Category
import com.univesp.game.model.Rent
import org.springframework.data.jpa.repository.JpaRepository

interface RentRepository: JpaRepository<Rent, Long> {
    fun findByCustomerId(customerId: Long): List<Rent>
}