package com.apjake.codeteststatemanagement.domain.validator

import com.apjake.codeteststatemanagement.domain.ValidationResult
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale

class DateValidator {
    private fun isValidDateFormat(date: String): Boolean {
        try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val d = formatter.parse(date)
            return true
        } catch (e: Exception) {
        }
        return false
    }
    operator fun invoke(value: String): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Date should not be empty"
            )
        } else if (!isValidDateFormat(value)) {
            ValidationResult(
                isSuccess = false,
                errorMessage = "Invalid date format"
            )
        } else {
            ValidationResult(
                isSuccess = true
            )
        }
    }
}