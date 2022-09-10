package com.apjake.codeteststatemanagement.domain.validator

import android.util.Patterns
import com.apjake.codeteststatemanagement.domain.ValidationResult

class EmailValidator {
    operator fun invoke(value: String): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Email should not be empty"
            )
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Invalid email address"
            )
        } else {
            ValidationResult(
                isSuccess = true
            )
        }
    }
}