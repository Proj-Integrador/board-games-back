package com.univesp.game.repository

import com.univesp.game.model.Game
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository: JpaRepository<Game, Long> {
}