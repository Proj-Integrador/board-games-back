package com.univesp.game.mapper

import com.univesp.game.factory.CustomerFactory
import com.univesp.game.factory.GameFactory
import com.univesp.game.factory.NewRentFormFactory
import com.univesp.game.service.CustomerService
import com.univesp.game.service.GameService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class NewRentFormMapperTest {

    @Mock
    private lateinit var gameService: GameService

    @Mock
    private lateinit var customerService: CustomerService

    @InjectMocks
    private lateinit var newRentFormMapper: NewRentFormMapper

    @Test
    fun map() {

        val form = NewRentFormFactory().create()

        val mockGame = GameFactory().create()
        val mockCustomer = CustomerFactory().create()

        `when`(gameService.searchById(1L)).thenReturn(mockGame)
        `when`(customerService.searchById(2L)).thenReturn(mockCustomer)

        val result = newRentFormMapper.map(form)

        assert(result.customer == mockCustomer)
        assert(result.customer.id == form.customerId)
        assert(result.game == mockGame)
        assert(result.rentDate == LocalDate.now())
        assert(result.daysRented == form.daysRented)
        assert(result.originalPrice == 3 * mockGame.pricePerDay)
        assert(result.note == form.note)
    }
}