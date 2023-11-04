package com.univesp.game.factory

import com.univesp.game.dto.NewCustomerForm
import java.time.LocalDate

class NewCustomerFormFactory {
    fun create(): NewCustomerForm {
        return NewCustomerForm(
            "cool name",
            "0011223344",
            "22266677799",
            LocalDate.now(),
            "cool address",
            "cool@email.com"
        )
    }
}