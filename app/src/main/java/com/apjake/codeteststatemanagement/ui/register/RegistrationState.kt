package com.apjake.codeteststatemanagement.ui.register

data class RegistrationState(
    val firstName: String = "",
    val firstNameError: String? = null,

    val lastName: String = "",
    val lastNameError: String? = null,

    val email: String = "",
    val emailError: String? = null,

    val date: String = "",
    val dateError: String? = null,

    val nationality: String = "",
    val nationalityError: String? = null,

    val country: String = "",
    val countryError: String? = null,

    val phonePrefix: String = "",
    val phonePrefixError: String? = null,

    val phoneNumber: String = "",
    val phoneNumberError: String? = null,

    val isFemale: Boolean = true,
){
    fun clearErrors(): RegistrationState{
        return this.copy(
            firstNameError = null,
            lastNameError = null,
            emailError = null,
            dateError = null,
            nationalityError = null,
            countryError = null,
            phonePrefixError = null,
            phoneNumberError = null,
        )
    }
}