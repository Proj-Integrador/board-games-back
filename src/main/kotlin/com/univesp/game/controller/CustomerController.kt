package com.univesp.game.controller

import com.univesp.game.dto.NewCustomerForm
import com.univesp.game.dto.UpdateCustomerForm
import com.univesp.game.model.Customer
import com.univesp.game.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/customers")
@CrossOrigin
class CustomerController(
    val service: CustomerService
) {

    @GetMapping
    fun listAll(
        @RequestParam(defaultValue = "") filter: String,
        @RequestParam(defaultValue = "asc") sort: String
    ): List<Customer> {
        return service.listAllFilteredByName(filter, Sort.by(Direction.fromString(sort), "name"))
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @RequestBody @Valid form: UpdateCustomerForm,
        @PathVariable id: Long
    ) {
        service.update(id, form)
    }
}