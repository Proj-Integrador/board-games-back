package com.univesp.game.controller

import com.univesp.game.dto.NewRentForm
import com.univesp.game.dto.RentView
import com.univesp.game.service.RentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rentals")
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

}