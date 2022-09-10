package com.apjake.codeteststatemanagement.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.apjake.codeteststatemanagement.databinding.FragmentRegisterBinding
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.CountryChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.DateChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.EmailChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.FirstNameChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.LastNameChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.NationalityChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.PhoneNoChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.PhonePrefixChanged
import com.apjake.codeteststatemanagement.ui.register.RegistrationEvent.Submit
import com.apjake.codeteststatemanagement.util.UseMe
import com.apjake.codeteststatemanagement.util.getUserPhonePrefix
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collectLatest

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var datePicker: MaterialDatePicker<Long>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        init()
        handleListeners()
        observeState()
        observeEvent()

        return binding.root
    }

    private fun init() {
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(UseMe.nowTimestamp())
            .build()
        datePicker.addOnPositiveButtonClickListener {
            viewModel.onEvent(DateChanged(it))
        }
        binding.edtPhonePrefix.setText(requireContext().getUserPhonePrefix())
    }

    private fun observeEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.event.collectLatest {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleListeners() {
        binding.edtDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnCreateNewAccount.setOnClickListener {
            viewModel.onEvent(Submit)
        }

        binding.edtFirstName.addTextChangedListener {
            viewModel.onEvent(FirstNameChanged(it.toString()))
        }
        binding.edtLastName.addTextChangedListener {
            viewModel.onEvent(LastNameChanged(it.toString()))
        }
        binding.edtEmail.addTextChangedListener {
            viewModel.onEvent(EmailChanged(it.toString()))
        }
        binding.edtNationality.addTextChangedListener {
            viewModel.onEvent(NationalityChanged(it.toString()))
        }
        binding.edtCountry.addTextChangedListener {
            viewModel.onEvent(CountryChanged(it.toString()))
        }
        binding.edtPhonePrefix.addTextChangedListener {
            viewModel.onEvent(PhonePrefixChanged(it.toString()))
        }
        binding.edtPhoneNo.addTextChangedListener {
            viewModel.onEvent(PhoneNoChanged(it.toString()))
        }
    }

    private fun showDatePicker() {
        datePicker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                with(state) {
                    binding.edtDate.setText(date)

                    binding.edtPhoneNo.error = phoneNumberError
                    binding.edtPhonePrefix.error = phonePrefixError
                    binding.edtCountry.error = countryError
                    binding.edtNationality.error = nationalityError
                    binding.edtDate.error = dateError
                    binding.edtEmail.error = emailError
                    binding.edtLastName.error = lastNameError
                    binding.edtFirstName.error = firstNameError

                    phoneNumberError?.let {
                        binding.edtPhoneNo.requestFocus()
                    }
                    phonePrefixError?.let {
                        binding.edtPhonePrefix.requestFocus()
                    }
                    countryError?.let {
                        binding.edtCountry.requestFocus()
                    }
                    nationalityError?.let {
                        binding.edtNationality.requestFocus()
                    }
                    dateError?.let {
                        binding.edtDate.requestFocus()
                    }
                    emailError?.let {
                        binding.edtEmail.requestFocus()
                    }
                    lastNameError?.let {
                        binding.edtLastName.requestFocus()
                    }
                    firstNameError?.let {
                        binding.edtFirstName.requestFocus()
                    }
                }
            }
        }
    }

}