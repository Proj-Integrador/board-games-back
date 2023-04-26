package com.univesp.game.mapper

import com.univesp.game.dto.NewCustomerForm
import com.univesp.game.model.Customer
import org.springframework.stereotype.Component

@Component
class NewCustomerMapper : Mapper<NewCustomerForm, Customer> {
    override fun map(t: NewCustomerForm): Customer {
        return Customer(
            name = t.name,
            phone = t.phone,
            cpf = t.cpf,
            birthday = t.birthday,
            address = t.address
        )
    }
}