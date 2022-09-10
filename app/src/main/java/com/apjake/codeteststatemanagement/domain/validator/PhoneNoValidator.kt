package com.apjake.codeteststatemanagement.domain.validator

import android.util.Patterns
import com.apjake.codeteststatemanagement.domain.ValidationResult

class PhoneNoValidator {

    operator fun invoke(value: String): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult(
                isSuccess = true
            )
        } else if (!Patterns.PHONE.matcher(value).matches()) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Invalid phone number"
            )
        } else {
            ValidationResult(
                isSuccess = true
            )
        }
    }

}