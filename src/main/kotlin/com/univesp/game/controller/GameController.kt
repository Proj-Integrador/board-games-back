package com.univesp.game.controller

import com.univesp.game.dto.GameView
import com.univesp.game.dto.NewGameForm
import com.univesp.game.mapper.GameViewMapper
import com.univesp.game.mapper.NewGameFormMapper
import com.univesp.game.service.GameService
import jakarta.validation.Valid
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/games")
@CrossOrigin
class GameController(
    val service: GameService,
    val gameFormMapper: NewGameFormMapper,
    val gameViewMapper: GameViewMapper
) {

    @GetMapping
    fun listAll(
        @RequestParam(defaultValue = "") filter: String,
        @RequestParam(defaultValue = "asc") sort: String
    ): List<GameView> {
        return service.listAllFilteredByName(filter, Sort.by(Sort.Direction.fromString(sort), "name"))
    }

    @GetMapping("/{id}")
    fun searchById(
        @PathVariable id: Long
    ): ResponseEntity<GameView>{
        val game = service.searchById(id)
        return ResponseEntity.ok(gameViewMapper.map(game))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @RequestBody @Valid form: NewGameForm
    ) {
        service.register(form)
    }
}