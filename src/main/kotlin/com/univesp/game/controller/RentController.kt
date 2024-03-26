package com.univesp.game.controller

import com.univesp.game.dto.NewRentForm
import com.univesp.game.dto.RentView
import com.univesp.game.service.RentService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rentals")
@CrossOrigin
class RentController(
    val service: RentService
) {
    @Operation(summary = "Get the list of all Rental")
    @GetMapping
    fun listAll(
        @RequestParam(defaultValue = "") filter: String,
        @RequestParam(defaultValue = "desc") sort: String
    ): List<RentView> {
        return service.listAllFilteredByCustomerName(filter, Sort.by(Sort.Direction.fromString(sort), "rentDate"))
    }

    @Operation(summary = "Create a Rental")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @RequestBody @Valid form: NewRentForm
    ) {
        service.register(form)
    }

    @Operation(summary = "Return a game in a Rental by rental id")
    @PostMapping("/{id}/return")
    @ResponseStatus(HttpStatus.OK)
    fun returnGame(
        @PathVariable id: Long
    ) {
        service.returnGame(id)
    }

    @Operation(summary = "Delete a Rental by rental id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteGame(
        @PathVariable id: Long
    ) {
        service.deleteGame(id)
    }

    @Operation(summary = "Get a Rental by the customer id")
    @GetMapping("/customer")
    fun listByCustomer(
        @RequestParam customerId: Int
    ): List<RentView> {
        return service.listByCustomer(customerId.toLong())
    }
}
