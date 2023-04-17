package com.univesp.game.controller

import com.univesp.game.dto.GameView
import com.univesp.game.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/games")
class GameController(
    val service: GameService
) {

    @GetMapping
    fun listAll(): List<GameView> {
        return service.listAll()
    }

    @GetMapping("/{id}")
    fun searchById(
        @PathVariable id: Long
    ): ResponseEntity<GameView>{
        return ResponseEntity.ok(service.searchById(id))
    }

//    @PostMapping
//    fun register(@RequestBody @Valid dto: NewGameForm) {
//        service.register(dto)
//    }
}