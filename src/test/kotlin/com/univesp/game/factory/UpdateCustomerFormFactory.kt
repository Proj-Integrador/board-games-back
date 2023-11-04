package com.univesp.game.factory

import com.univesp.game.dto.UpdateCustomerForm
import java.time.LocalDate

class UpdateCustomerFormFactory {
    fun create(): UpdateCustomerForm {
        return UpdateCustomerForm(
            "cool changed name",
            "00112233440",
            LocalDate.now(),
            "cool changed address",
            "coolchanged@email.com"
        )
    }
}