package com.univesp.game.controller

import com.univesp.game.dto.NewCustomerForm
import com.univesp.game.model.Customer
import com.univesp.game.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    val service: CustomerService //Declara as depenências no construtor. Spring saberá que quando instanciar controller o service deverá ser injetado
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
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody @Valid form: NewCustomerForm) {
        service.register(form)
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @RequestBody @Valid form: NewCustomerForm,
        @PathVariable id: Long
    ) {
        service.update(id, form)
    }
}