package com.univesp.game.controller

import com.univesp.game.dto.GameView
import com.univesp.game.dto.NewGameForm
import com.univesp.game.mapper.NewGameFormMapper
import com.univesp.game.service.GameService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/games")
class GameController(
    val service: GameService,
    val gameFormMapper: NewGameFormMapper
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

    @PostMapping
    fun register(
        @RequestBody @Valid form: NewGameForm
    ): ResponseEntity<GameView> {
        val game = service.register(form)
        return ResponseEntity.status(HttpStatus.CREATED).body(game)
    }
}