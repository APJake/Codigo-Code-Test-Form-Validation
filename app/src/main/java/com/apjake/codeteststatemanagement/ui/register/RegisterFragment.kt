package com.apjake.codeteststatemanagement.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.apjake.codeteststatemanagement.databinding.FragmentRegisterBinding
import com.apjake.codeteststatemanagement.util.UseMe
import com.apjake.codeteststatemanagement.util.dateString
import com.apjake.codeteststatemanagement.util.getUserPhonePrefix
import com.google.android.material.datepicker.MaterialDatePicker

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var datePicker: MaterialDatePicker<Long>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        init()
        handleListeners()
        handleFormValidation()

        return binding.root
    }

    private fun init() {
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(UseMe.nowTimestamp())
            .build()
        datePicker.addOnPositiveButtonClickListener {
            viewModel.date.postValue(it.dateString())
        }
        viewModel.phonePrefix.postValue(requireContext().getUserPhonePrefix())
    }

    private fun handleListeners() {
        binding.edtDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showDatePicker() {
        datePicker.show(parentFragmentManager,"MATERIAL_DATE_PICKER")
    }

    private fun handleFormValidation() {
        viewModel.isLoginFormValid.observe(this){
            if(it == true){
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.firstNameValidator.error.observe(this){
            binding.edtFirstName.error = it
            binding.edtFirstName.requestFocus()
        }
        viewModel.lastNameValidator.error.observe(this){
            binding.edtLastName.error = it
            binding.edtLastName.requestFocus()
        }
        viewModel.emailValidator.error.observe(this){
            binding.edtEmail.error = it
            binding.edtEmail.requestFocus()
        }
        viewModel.dateValidator.error.observe(this){
            binding.edtDate.error = it
            binding.edtDate.requestFocus()
        }
        viewModel.nationalityValidator.error.observe(this){
            binding.edtNationality.error = it
            binding.edtNationality.requestFocus()
        }
        viewModel.countryValidator.error.observe(this){
            binding.edtCountry.error = it
            binding.edtCountry.requestFocus()
        }
        viewModel.phonePrefixValidator.error.observe(this){
            binding.edtPhonePrefix.error = it
            binding.edtPhonePrefix.requestFocus()
        }
        viewModel.phoneNoValidator.error.observe(this){
            binding.edtPhoneNo.error = it
            binding.edtPhoneNo.requestFocus()
        }
    }

}