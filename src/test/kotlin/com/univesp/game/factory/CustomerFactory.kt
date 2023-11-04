package com.univesp.game.factory

import com.univesp.game.model.Customer
import java.time.LocalDate

class CustomerFactory {

    fun create(): Customer {
        return Customer(
            2L,
            "cool name",
            "0011223344",
            "22266677799",
            LocalDate.now(),
            "cool address",
            "cool@email.com"
        )
    }
}