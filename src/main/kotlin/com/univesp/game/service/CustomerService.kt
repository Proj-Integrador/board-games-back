package com.univesp.game.service

import com.univesp.game.exception.NotFoundException
import com.univesp.game.model.Customer
import com.univesp.game.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val repository: CustomerRepository
) {

    fun listAll(): List<Customer> {
        return repository.findAll()
    }

    fun searchById(id: Long): Customer {
        return repository.findById(id)
            .orElseThrow{NotFoundException("Cliente não encontrado")}
    }

    fun register(customer: Customer): Customer {
        return repository.save(customer)
    }
}