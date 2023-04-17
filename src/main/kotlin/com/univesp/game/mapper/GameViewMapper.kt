package com.univesp.game.mapper

import com.univesp.game.dto.GameView
import com.univesp.game.model.Game
import org.springframework.stereotype.Component

@Component
class GameViewMapper : Mapper<Game, GameView> {
    override fun map(t: Game): GameView {
        return GameView(
            id = t.id,
            name = t.name,
            image = t.image,
            stockTotal = t.stockTotal,
            categoryId = t.category.id,
            pricePerDay = t.pricePerDay,
            ludopediaLink = t.ludopediaLink,
            categoryName = t.category.name
        )
    }
}