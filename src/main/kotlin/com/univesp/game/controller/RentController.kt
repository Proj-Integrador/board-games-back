package com.univesp.game.controller

import com.univesp.game.dto.NewRentForm
import com.univesp.game.dto.RentView
import com.univesp.game.service.RentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rentals")
@CrossOrigin("*")
class RentController(
    val service: RentService
) {
    @GetMapping
    fun listAll(): List<RentView> {
        return service.listAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @RequestBody @Valid form: NewRentForm
    ) {
        service.register(form)
    }

    @PostMapping("/{id}/return")
    @ResponseStatus(HttpStatus.OK)
    fun returnGame(
        @PathVariable id: Long
    ) {
        service.returnGame(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteGame(
        @PathVariable id: Long
    ) {
        service.deleteGame(id)
    }

    @GetMapping("/{customerId}")
    fun listByCustomer(
        @PathVariable customerId: Long
    ): List<RentView> {
        return service.listByCustomer(customerId)
    }
}
