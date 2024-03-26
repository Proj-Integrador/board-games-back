package com.univesp.game.controller

import com.univesp.game.dto.NewCustomerForm
import com.univesp.game.dto.UpdateCustomerForm
import com.univesp.game.model.Customer
import com.univesp.game.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
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

    @Operation(summary = "Get the list of all Customers filtered and sorted by name")
    @GetMapping
    fun listAll(
        @RequestParam(defaultValue = "") filter: String,
        @RequestParam(defaultValue = "asc") sort: String
    ): List<Customer> {
        return service.listAllFilteredByName(filter, Sort.by(Direction.fromString(sort), "name"))
    }

    @Operation(summary = "Get a Customer by its id")
    @GetMapping("/{id}")
    fun searchById(
        @PathVariable id: Long
    ): ResponseEntity<Customer>{
        return ResponseEntity.ok(service.searchById(id))
    }

    @Operation(summary = "Create a Customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody @Valid form: NewCustomerForm) {
        service.register(form)
    }

    @Operation(summary = "Update a Customer")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @RequestBody @Valid form: UpdateCustomerForm,
        @PathVariable id: Long
    ) {
        service.update(id, form)
    }
}