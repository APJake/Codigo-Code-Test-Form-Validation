package com.apjake.codeteststatemanagement.domain.validator

import com.apjake.codeteststatemanagement.domain.ValidationResult

class PhonePrefixValidator{

    private fun validCountryCode(code: String): Boolean {
        val regex = Regex("""^(\+?\d{1,3}|\d{1,4})$""")
        return regex matches code
    }

    operator fun invoke(value: String, phone: String): ValidationResult {
        return if (value.isBlank() && phone.isBlank()) {
            ValidationResult(
                isSuccess = true
            )
        } else if (!validCountryCode(value)) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Invalid country code"
            )
        } else {
            ValidationResult(
                isSuccess = true
            )
        }
    }

}