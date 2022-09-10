package com.apjake.codeteststatemanagement.domain

data class ValidationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null
)