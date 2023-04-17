package com.univesp.game.service

import com.univesp.game.dto.RentView
import com.univesp.game.mapper.RentViewMapper
import com.univesp.game.repository.RentRepository
import org.springframework.stereotype.Service

@Service
class RentService(
    private val repository: RentRepository,
    var rentViewMapper: RentViewMapper
) {
    fun listAll(): List<RentView> {
        val rentals = repository.findAll()
        return rentals.map { t ->
            rentViewMapper.map(t)
        }
    }
}