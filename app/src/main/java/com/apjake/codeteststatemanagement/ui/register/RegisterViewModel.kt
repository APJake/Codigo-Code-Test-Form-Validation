package com.apjake.codeteststatemanagement.ui.register

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apjake.codeteststatemanagement.util.LiveDataValidator
import com.apjake.codeteststatemanagement.util.LiveDataValidatorResolver
import com.apjake.codeteststatemanagement.util.validDateFormat
import com.apjake.codeteststatemanagement.util.validPhonePrefix

class RegisterViewModel: ViewModel() {

    val firstName = MutableLiveData<String>()
    val firstNameValidator = LiveDataValidator(firstName).apply {
        //Whenever the condition of the predicate is true, the error message should be emitted
        addRule("First name is required") { it.isNullOrBlank() }
    }

    val lastName = MutableLiveData<String>()
    val lastNameValidator = LiveDataValidator(lastName).apply {
        addRule("Last name is required") { it.isNullOrBlank() }
    }

    val email = MutableLiveData<String>()
    val emailValidator = LiveDataValidator(email).apply {
        addRule("Email is required") { it.isNullOrBlank() }
        addRule("Invalid email") { it.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(it).matches()}
    }

    val date = MutableLiveData<String>()
    val dateValidator = LiveDataValidator(date).apply {
        addRule("Birth date is required") { it.isNullOrBlank() }
        addRule("Invalid date format") { !it.validDateFormat()}
    }

    val nationality = MutableLiveData<String>()
    val nationalityValidator = LiveDataValidator(nationality).apply {
        addRule("Nationality is required") { it.isNullOrBlank() }
    }

    val country = MutableLiveData<String>()
    val countryValidator = LiveDataValidator(country).apply {
        addRule("Country is required") { it.isNullOrBlank() }
    }

    val phonePrefix = MutableLiveData<String>()
    val phonePrefixValidator = LiveDataValidator(phonePrefix).apply {
        addRule("allowed only '+' and digits") { !it.isNullOrBlank() && !it.validPhonePrefix() }
    }

    val phoneNo = MutableLiveData<String>()
    val phoneNoValidator = LiveDataValidator(phoneNo).apply {
        addRule("Invalid phone no.") { !it.isNullOrBlank() && !Patterns.PHONE.matcher(it).matches() }
    }

    val isLoginFormValid = MutableLiveData<Boolean>(false)

    fun validateForm() {
        val validators = listOf(firstNameValidator, lastNameValidator, emailValidator, dateValidator,
            nationalityValidator, countryValidator, phonePrefixValidator, phoneNoValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        isLoginFormValid.postValue(validatorResolver.isValid())
    }

}