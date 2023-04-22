package com.univesp.game.dto

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class NewCustomerForm(
    @field:NotBlank(message = "O nome é obrigatório.")
    val name: String,

    @field:NotBlank(message = "O telefone é obrigatório.")
    @field:Size(min = 10, max = 11, message = "Telefone deve ter entre 10 e 11 dígitos.")
    @field:Pattern(regexp="[\\d]*", message = "O campo telefone aceita apenas numeros.")
    val phone: String,

    @field:NotBlank(message = "O CPF é obrigatório.")
    @field:Pattern(regexp="[\\d]{11}", message = "O campo CPF deve conter 11 dígitos.")
    val cpf: String,

    @field:NotNull(message = "A data de nascimento é obrigatória.")
    val birthday: LocalDate,

    @field:NotBlank(message = "O Endereço é obrigatório.")
    val address: String
) {
}