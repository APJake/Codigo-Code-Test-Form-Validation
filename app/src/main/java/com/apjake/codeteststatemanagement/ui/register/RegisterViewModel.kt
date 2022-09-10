package com.apjake.codeteststatemanagement.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apjake.codeteststatemanagement.domain.validator.CountryValidator
import com.apjake.codeteststatemanagement.domain.validator.DateValidator
import com.apjake.codeteststatemanagement.domain.validator.EmailValidator
import com.apjake.codeteststatemanagement.domain.validator.FirstNameValidator
import com.apjake.codeteststatemanagement.domain.validator.LastNameValidator
import com.apjake.codeteststatemanagement.domain.validator.NationalityValidator
import com.apjake.codeteststatemanagement.domain.validator.PhoneNoValidator
import com.apjake.codeteststatemanagement.domain.validator.PhonePrefixValidator
import com.apjake.codeteststatemanagement.ui.register.RegisterViewModel.ValidationEvent.Success
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.CountryChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.DateChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.EmailChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.FirstNameChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.GenderChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.LastNameChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.NationalityChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.PhoneNoChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.PhonePrefixChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.Submit
import com.apjake.codeteststatemanagement.util.UseMe
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val validators: Validators = Validators(),
) : ViewModel() {

    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    private val _event = Channel<ValidationEvent>()
    val event = _event.receiveAsFlow()

    fun onEvent(event: RegistrationEvent) {
        _state.value = _state.value.clearErrors()
        when (event) {
            is CountryChanged -> {
                _state.value = _state.value.copy(
                    country = event.value
                )
            }
            is DateChanged -> {
                _state.value = _state.value.copy(
                    date = UseMe.toDateString(event.value)
                )
            }
            is EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }
            is FirstNameChanged -> {
                _state.value = _state.value.copy(
                    firstName = event.value
                )
            }
            is GenderChanged -> {
                _state.value = _state.value.copy(
                    isFemale = event.isFemale
                )
            }
            is LastNameChanged -> {
                _state.value = _state.value.copy(
                    lastName = event.value
                )
            }
            is NationalityChanged -> {
                _state.value = _state.value.copy(
                    nationality = event.value
                )
            }
            is PhoneNoChanged -> {
                _state.value = _state.value.copy(
                    phoneNumber = event.value
                )
            }
            is PhonePrefixChanged -> {
                _state.value = _state.value.copy(
                    phonePrefix = event.value
                )
            }
            Submit -> {
                register()
            }
        }
    }

    private fun register() {
        val firstNameResult = validators.firstName(_state.value.firstName)
        val lastNameResult = validators.lastName(_state.value.lastName)
        val emailResult = validators.email(_state.value.email)
        val dateResult = validators.date(_state.value.date)
        val nationalityResult = validators.nationality(_state.value.nationality)
        val countryResult = validators.country(_state.value.country)
        val phonePrefixResult =
            validators.phonePrefix(_state.value.phonePrefix, _state.value.phoneNumber)
        val phoneNoResult = validators.phoneNo(_state.value.phoneNumber)

        val hasErrors = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            dateResult,
            nationalityResult,
            countryResult,
            phonePrefixResult,
            phoneNoResult
        ).any { !it.isSuccess }

        if (hasErrors) {
            _state.value = _state.value.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                dateError = dateResult.errorMessage,
                nationalityError = nationalityResult.errorMessage,
                countryError = countryResult.errorMessage,
                phonePrefixError = phonePrefixResult.errorMessage,
                phoneNumberError = phoneNoResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            _event.send(Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

    data class Validators(
        val firstName: FirstNameValidator = FirstNameValidator(),
        val lastName: LastNameValidator = LastNameValidator(),
        val email: EmailValidator = EmailValidator(),
        val date: DateValidator = DateValidator(),
        val nationality: NationalityValidator = NationalityValidator(),
        val country: CountryValidator = CountryValidator(),
        val phonePrefix: PhonePrefixValidator = PhonePrefixValidator(),
        val phoneNo: PhoneNoValidator = PhoneNoValidator(),
    )
}