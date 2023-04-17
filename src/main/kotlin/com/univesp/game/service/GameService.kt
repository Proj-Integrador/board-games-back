package com.univesp.game.service

import com.univesp.game.dto.GameView
import com.univesp.game.exception.NotFoundException
import com.univesp.game.mapper.GameViewMapper
import com.univesp.game.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val repository: GameRepository,
    var gameViewMapper: GameViewMapper
) {
    fun listAll(): List<GameView> {
        val games = repository.findAll()
        return games.map { t ->
            gameViewMapper.map(t)
        }
    }

    fun searchById(id: Long): GameView {
        val game = repository.findById(id)
            .orElseThrow { NotFoundException("Jogo não encontrado.") }
        return gameViewMapper.map(game)
    }
}