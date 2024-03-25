package com.univesp.game.repository

import com.univesp.game.model.Customer
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long> {
    fun findByCpf(cpf: String): List<Customer>

    fun findByNameContainingIgnoreCase(name: String, sort: Sort): List<Customer>
}