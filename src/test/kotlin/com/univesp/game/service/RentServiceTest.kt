package com.univesp.game.service

import com.univesp.game.exception.GeneralBadRequestException
import com.univesp.game.exception.NotFoundException
import com.univesp.game.factory.GameFactory
import com.univesp.game.factory.RentFactory
import com.univesp.game.mapper.NewRentFormMapper
import com.univesp.game.mapper.RentViewMapper
import com.univesp.game.repository.GameRepository
import com.univesp.game.repository.RentRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class RentServiceTest {

    @Mock
    lateinit var repository: RentRepository

    @Mock
    lateinit var rentViewMapper: RentViewMapper

    @Mock
    lateinit var newRentFormMapper: NewRentFormMapper

    @Mock
    lateinit var gameRepository: GameRepository

    @InjectMocks
    lateinit var service: RentService

    var rentMock = RentFactory().create()
    var gameMock = GameFactory().create()

    @Test
    fun listAll() {
        `when`(repository.findAll())
            .thenReturn(listOf(rentMock))
        val rentView = listOf(rentMock).map { t -> rentViewMapper.map(t) }
        val result = service.listAll()
        assertEquals(rentView, result)
    }


    @Test
    fun searchById() {
        `when`(repository.findById(rentMock.id!!))
            .thenReturn(Optional.of(rentMock))

        val result = service.searchById(rentMock.id!!)
        assertEquals(rentMock, result)
    }

    @Test
    fun testSearchNonExistentRentById() {
        val nonExistentRentId = 200L
        `when`(repository.findById(nonExistentRentId))
            .thenReturn(Optional.empty())

        Assertions.assertThrows(NotFoundException::class.java) {
            service.searchById(nonExistentRentId)
        }
    }

    @Test
    fun `try to delete game already returned, then exception`() {
        `when`(repository.findById(rentMock.id!!))
            .thenReturn(Optional.of(rentMock))
        Assertions.assertThrows(GeneralBadRequestException::class.java) {
            service.deleteGame(rentMock.id!!)
        }
    }

    @Test
    fun listByCustomer() {
        `when`(repository.findByCustomerId(anyLong()))
            .thenReturn(listOf(rentMock))
        val rentView = listOf(rentMock).map { t -> rentViewMapper.map(t) }
        val result = service.listByCustomer(2L)
        assertEquals(rentView, result)
    }

    @Test
    fun `getRentAvailability when there is no game available`() {
        gameMock.stockTotal = 2
        `when`(gameRepository.findById(2L))
            .thenReturn(Optional.of(gameMock))

        rentMock.returnDate = null
        `when`(repository.findByGameId(2L))
            .thenReturn(listOf(rentMock, rentMock, rentMock))

        val result = service.getRentAvailability(2L)
        assertEquals(false, result)
    }

    @Test
    fun `getRentAvailability when there is game available`() {
        gameMock.stockTotal = 10
        `when`(gameRepository.findById(2L))
            .thenReturn(Optional.of(gameMock))

        rentMock.returnDate = null
        `when`(repository.findByGameId(2L))
            .thenReturn(listOf(rentMock, rentMock, rentMock))

        val result = service.getRentAvailability(2L)
        assertEquals(true, result)
    }
}