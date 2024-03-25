package com.univesp.game.service

import com.univesp.game.dto.GameView
import com.univesp.game.dto.NewGameForm
import com.univesp.game.exception.NotFoundException
import com.univesp.game.exception.RecordAlreadyExistsException
import com.univesp.game.mapper.GameViewMapper
import com.univesp.game.mapper.NewGameFormMapper
import com.univesp.game.model.Game
import com.univesp.game.repository.GameRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class GameService(
    private val repository: GameRepository,
    var gameViewMapper: GameViewMapper,
    val newGameFormMapper: NewGameFormMapper
) {
    fun listAll(): List<GameView> {
        val games = repository.findAll()
        return games.map { t ->
            gameViewMapper.map(t)
        }
    }

    fun searchById(id: Long): Game {
        return repository.findById(id)
            .orElseThrow { NotFoundException("Jogo não encontrado.") }
    }

    fun searchByName(name: String): List<Game> {
        return repository.findByNameIgnoreCase(name)
    }

    fun register(gameForm: NewGameForm): GameView {
        val games = searchByName(gameForm.name)
        if(games.isNotEmpty()){
            throw RecordAlreadyExistsException("Já existe um Jogo com este nome cadastrado.")
        }
        val game = newGameFormMapper.map(gameForm)
        val createdGame = repository.save(game)
        return gameViewMapper.map(createdGame)
    }

    fun listAllFilteredByName(filter: String, sort: Sort): List<GameView> {
        val games = repository.findByNameContainingIgnoreCase(filter, sort)

        return games.map { t ->
            gameViewMapper.map(t)
        }
    }

}