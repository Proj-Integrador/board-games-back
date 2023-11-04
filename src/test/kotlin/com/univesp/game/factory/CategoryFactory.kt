package com.univesp.game.factory

import com.univesp.game.model.Category

class CategoryFactory {
    fun create(): Category {
        return Category(
            22L,
            "cool category name"
        )
    }
}