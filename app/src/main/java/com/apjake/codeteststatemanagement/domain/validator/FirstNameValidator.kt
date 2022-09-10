package com.apjake.codeteststatemanagement.domain.validator

import com.apjake.codeteststatemanagement.domain.ValidationResult

class FirstNameValidator {
    operator fun invoke(value: String): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "First name should not be empty"
            )
        } else {
            ValidationResult(
                isSuccess = true
            )
        }
    }
}