package com.univesp.game.service

import com.univesp.game.dto.NewRentForm
import com.univesp.game.dto.RentView
import com.univesp.game.exception.GeneralBadRequestException
import com.univesp.game.exception.NotFoundException
import com.univesp.game.mapper.NewRentFormMapper
import com.univesp.game.mapper.RentViewMapper
import com.univesp.game.model.Rent
import com.univesp.game.repository.RentRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RentService(
    private val repository: RentRepository,
    var rentViewMapper: RentViewMapper,
    val newRentFormMapper: NewRentFormMapper
) {
    fun listAll(): List<RentView> {
        val rentals = repository.findAll()
        return rentals.map { t ->
            rentViewMapper.map(t)
        }
    }

    fun register(form: NewRentForm) {
        val rent = newRentFormMapper.map(form)
        repository.save(rent)
    }

    fun searchById(id: Long): Rent {
        return repository.findById(id)
            .orElseThrow { NotFoundException("Aluguel não encontrado.") }
    }

    fun returnGame(id: Long) {
        var rent = searchById(id)
        if (rent.returnDate != null){
            throw GeneralBadRequestException("Aluguel já finalizado.")
        }
        rent.returnDate = LocalDate.now()

        var maxReturnDateWithoutDelayFee = rent.rentDate.plusDays(rent.daysRented!!.toLong())

        var daysDelayed = rent.returnDate!!.compareTo(maxReturnDateWithoutDelayFee)

        if(daysDelayed > 0){
            rent.delayFee = daysDelayed * rent.originalPrice
        } else {
            rent.delayFee = 0
        }
        repository.save(rent)
    }

    fun deleteGame(id: Long) {
        var rent = searchById(id)

        if(rent.returnDate != null) {
            throw GeneralBadRequestException("Este aluguel não pode ser apagado pois já foi finalizado.")
        }

        repository.deleteById(id)
    }

    fun listByCustomer(customerId: Long): List<RentView> {
        val rentals = repository.findByCustomerId(customerId)
        return rentals.map { t ->
            rentViewMapper.map(t)
        }
    }
}