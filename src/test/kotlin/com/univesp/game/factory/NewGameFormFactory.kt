package com.univesp.game.factory

import com.univesp.game.dto.NewGameForm

class NewGameFormFactory {

    fun create(): NewGameForm {
        return NewGameForm(
            "cool name",
            "cool image",
            2,
            30L,
            400,
            "cool link"
        )
    }
}