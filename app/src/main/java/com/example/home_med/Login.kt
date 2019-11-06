package com.example.home_med

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentLoginBinding


class Login : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LoginDirections.actionLoginToHome2())
        }
        binding.registerButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LoginDirections.actionLoginToRegister())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

}
