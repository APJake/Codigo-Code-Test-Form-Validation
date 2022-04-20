package com.apjake.codeteststatemanagement.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.apjake.codeteststatemanagement.R
import com.apjake.codeteststatemanagement.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnCreateNewAccount.setOnClickListener {
            goToRegisterPage()
        }
        binding.ibClose.setOnClickListener {
            goToRegisterPage()
        }

        return binding.root
    }

    private fun goToRegisterPage(){
        findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
    }

}