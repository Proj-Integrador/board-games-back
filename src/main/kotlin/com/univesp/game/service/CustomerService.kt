package com.univesp.game.service

import com.univesp.game.dto.NewCustomerForm
import com.univesp.game.exception.NotFoundException
import com.univesp.game.exception.RecordAlreadyExistsException
import com.univesp.game.mapper.NewCustomerMapper
import com.univesp.game.model.Customer
import com.univesp.game.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service //Classe gerenciada pelo Spring. Pode-se injetar em qualquer classe também gerenciada pelo Spring. Na classe que se quer injetar -> Declarar construtor que recebe como parâmetro um Service
class CustomerService(
    private val repository: CustomerRepository,
    private val newCustomerMapper: NewCustomerMapper
) {

    fun listAll(): List<Customer> {
        return repository.findAll()
    }

    fun searchById(id: Long): Customer {
        return repository.findById(id)
            .orElseThrow{NotFoundException("Cliente não encontrado")}
    }

    fun checkCpf(form: NewCustomerForm) {
        val customers = repository.findByCpf(form.cpf)
        if(customers.isNotEmpty()){
            throw RecordAlreadyExistsException("CPF Já cadastrado.")
        }
    }

    fun register(form: NewCustomerForm): Customer {
        checkCpf(form)
        val customer = newCustomerMapper.map(form)
        return repository.save(customer)
    }

    fun update(id: Long, form: NewCustomerForm) {
        checkCpf(form)

        var customer = searchById(id)

        customer.name = form.name
        customer.phone = form.phone
        customer.cpf = form.cpf
        customer.birthday = form.birthday
        customer.address = form.address

        repository.save(customer)
    }
}