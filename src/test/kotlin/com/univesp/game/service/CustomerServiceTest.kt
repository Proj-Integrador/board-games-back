package com.univesp.game.service

import com.univesp.game.exception.NotFoundException
import com.univesp.game.exception.RecordAlreadyExistsException
import com.univesp.game.factory.CustomerFactory
import com.univesp.game.factory.NewCustomerFormFactory
import com.univesp.game.factory.UpdateCustomerFormFactory
import com.univesp.game.mapper.NewCustomerMapper
import com.univesp.game.repository.CustomerRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class CustomerServiceTest {

    @Mock
    lateinit var repository: CustomerRepository

    @Mock
    lateinit var mapper: NewCustomerMapper

    @InjectMocks
    lateinit var service: CustomerService

    private val customerMock = CustomerFactory().create()
    private val newCustomerForm = NewCustomerFormFactory().create()

    @Test
    fun listAll() {
        `when`(repository.findAll()).thenReturn(listOf(customerMock))
        val result = service.listAll()
        assertEquals(listOf(customerMock), result)
    }

    @Test
    fun searchById() {
        `when`(repository.findById(2L)).thenReturn(Optional.of(customerMock))
        val result = service.searchById(2L)
        assertEquals(customerMock, result)
    }

    @Test
    fun `When search customer by id returns nothing throw exception`() {
        `when`(repository.findById(3L)).thenThrow(NotFoundException("Cliente não encontrado"))
       Assertions.assertThrows(NotFoundException::class.java) {
           service.searchById(3L)
       }
    }

    @Test
    fun `when checkCpf finds a already register cpf, throw exception`() {
        `when`(repository.findByCpf("22266677799")).thenReturn(listOf(customerMock))
        Assertions.assertThrows(RecordAlreadyExistsException::class.java) {
            service.checkCpf("22266677799")
        }
    }

    @Test
    fun `when register new customer, return customer`() {
        `when`(repository.findByCpf(anyString()))
            .thenReturn(listOf())
        `when`(repository.save(any())).thenReturn(customerMock)

        var result = service.register(newCustomerForm)
        assertEquals(customerMock, result)
    }

    @Test
    fun `when trying to register existing customer, thow exception`() {
        `when`(repository.findByCpf("22266677799"))
            .thenReturn(listOf(customerMock))

        assertThrows(RecordAlreadyExistsException::class.java) {
            service.register(NewCustomerFormFactory().create())
        }
    }

    @Test
    fun update() {
        val existingCustomer = customerMock

        val form = UpdateCustomerFormFactory().create()

        `when`(repository.findById(anyLong())).thenReturn(Optional.of(existingCustomer))

        service.update(2L, form)

        assertEquals(form.name, existingCustomer.name)
        assertEquals(form.phone, existingCustomer.phone)
        assertEquals(form.birthday, existingCustomer.birthday)
        assertEquals(form.address, existingCustomer.address)
        assertEquals(form.email, existingCustomer.email)
    }
}