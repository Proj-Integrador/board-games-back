package com.univesp.game.mapper

import com.univesp.game.dto.NewGameForm
import com.univesp.game.model.Game
import com.univesp.game.service.CategoryService
import org.springframework.stereotype.Component

@Component
class NewGameFormMapper(
    val categoryService: CategoryService
) : Mapper<NewGameForm, Game>{
    override fun map(t: NewGameForm): Game {
        return Game(
            name = t.name,
            image = t.image,
            stockTotal = t.stockTotal,
            category = categoryService.searchById(t.categoryId),
            pricePerDay = t.pricePerDay,
            ludopediaLink = t.ludopediaLink
        )
    }
}