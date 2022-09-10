package com.apjake.codeteststatemanagement.ui.register

sealed class RegistrationEvent {
    data class FirstNameChanged(val value: String): RegistrationEvent()
    data class LastNameChanged(val value: String): RegistrationEvent()
    data class EmailChanged(val value: String): RegistrationEvent()
    data class DateChanged(val value: Long): RegistrationEvent()
    data class GenderChanged(val isFemale: Boolean): RegistrationEvent()
    data class NationalityChanged(val value: String): RegistrationEvent()
    data class CountryChanged(val value: String): RegistrationEvent()
    data class PhonePrefixChanged(val value: String): RegistrationEvent()
    data class PhoneNoChanged(val value: String): RegistrationEvent()

    object Submit: RegistrationEvent()
}