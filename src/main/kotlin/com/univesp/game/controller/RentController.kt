package com.univesp.game.controller

import com.univesp.game.dto.RentView
import com.univesp.game.service.RentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
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
}