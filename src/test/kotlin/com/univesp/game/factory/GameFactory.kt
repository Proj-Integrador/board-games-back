package com.univesp.game.factory

import com.univesp.game.model.Category
import com.univesp.game.model.Game

class GameFactory {
    fun create(): Game {
        return Game(
            1L,
            "cool name",
            "cool image",
            2,
            Category(3L, "cool category name"),
            4,
            "cool ludopediaLink"
        )
    }
}