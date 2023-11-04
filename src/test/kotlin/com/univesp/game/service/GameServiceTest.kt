package com.univesp.game.service

import com.univesp.game.exception.NotFoundException
import com.univesp.game.exception.RecordAlreadyExistsException
import com.univesp.game.factory.GameFactory
import com.univesp.game.factory.NewGameFormFactory
import com.univesp.game.mapper.GameViewMapper
import com.univesp.game.mapper.NewGameFormMapper
import com.univesp.game.repository.GameRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
class GameServiceTest {

    @Mock
    lateinit var repository: GameRepository

    @Mock
    lateinit var viewMapper: GameViewMapper

    @Mock
    lateinit var formMapper: NewGameFormMapper

    @InjectMocks
    lateinit var service: GameService

    var gameMock = GameFactory().create()
    var gameForm = NewGameFormFactory().create()

    @Test
    fun listAll() {
        `when`(repository.findAll())
            .thenReturn(listOf(gameMock))
        val gameView = listOf(gameMock).map { t -> viewMapper.map(t) }
        val result = service.listAll()
        assertEquals(gameView, result)
    }

    @Test
    fun searchById() {
        `when`(repository.findById(gameMock.id!!))
            .thenReturn(Optional.of(gameMock))

        val result = service.searchById(gameMock.id!!)

        assertEquals(gameMock, result)
    }

    @Test
    fun testSearchNonExistentGameById() {
        val nonExistentGameId = 200L
        `when`(repository.findById(nonExistentGameId))
            .thenReturn(Optional.empty())

        Assertions.assertThrows(NotFoundException::class.java) {
            service.searchById(nonExistentGameId)
        }
    }


    @Test
    fun searchByName() {
        `when`(repository.findByNameIgnoreCase(anyString()))
            .thenReturn(listOf(gameMock))
        val result = service.searchByName("some name")
        assertEquals(listOf(gameMock), result)
    }

    @Test
    fun testRegisterGameWithDuplicateName() {
        `when`(repository.findByNameIgnoreCase(gameMock.name))
            .thenReturn(listOf(gameMock))

        Assertions.assertThrows(RecordAlreadyExistsException::class.java){
            service.register(gameForm)
        }
    }
}