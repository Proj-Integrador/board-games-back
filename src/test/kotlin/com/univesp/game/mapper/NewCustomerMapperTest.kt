package com.univesp.game.mapper

import com.univesp.game.factory.NewCustomerFormFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NewCustomerMapperTest {

    @Test
    fun map() {
        val expected = NewCustomerFormFactory().create()

        val result = NewCustomerMapper().map(expected)

        assertEquals(expected.name, result.name)
        assertEquals(expected.phone, result.phone)
        assertEquals(expected.cpf, result.cpf)
        assertEquals(expected.birthday, result.birthday)
        assertEquals(expected.address, result.address)
        assertEquals(expected.email, result.email)
    }
}