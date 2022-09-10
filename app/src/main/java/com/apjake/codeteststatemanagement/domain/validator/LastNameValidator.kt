package com.apjake.codeteststatemanagement.domain.validator

import com.apjake.codeteststatemanagement.domain.ValidationResult

class LastNameValidator {
    operator fun invoke(value: String): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Last name should not be empty"
            )
        } else {
            ValidationResult(
                isSuccess = true
            )
        }
    }
}