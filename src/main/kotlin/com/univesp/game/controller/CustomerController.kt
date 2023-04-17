package com.univesp.game.controller

import com.univesp.game.model.Customer
import com.univesp.game.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    val service: CustomerService
) {

    @GetMapping
    fun listAll(): List<Customer> {
        return service.listAll()
    }

    @GetMapping("/{id}")
    fun searchById(
        @PathVariable id: Long
    ): ResponseEntity<Customer>{
        return ResponseEntity.ok(service.searchById(id))
    }

    @PostMapping
    fun register(@RequestBody customer: Customer) {
        service.register(customer)
    }
}