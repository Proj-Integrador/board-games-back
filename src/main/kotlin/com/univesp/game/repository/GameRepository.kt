package com.univesp.game.repository

import com.univesp.game.model.Game
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GameRepository: JpaRepository<Game, Long> {
    fun findByNameIgnoreCase(name: String): List<Game>
    override fun findById(id: Long): Optional<Game>
    fun findByNameContainingIgnoreCase(filter: String, sort: Sort): List<Game>
}