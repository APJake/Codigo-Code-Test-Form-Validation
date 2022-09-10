package com.apjake.codeteststatemanagement.domain.validator

import com.apjake.codeteststatemanagement.domain.ValidationResult

class CountryValidator {
    operator fun invoke(value: String): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Country should not be empty"
            )
        } else {
            ValidationResult(
                isSuccess = true
            )
        }
    }
    fun getFormatted(value: String): String{
        return value.replaceFirstChar { it.uppercaseChar() }.trim()
    }
}