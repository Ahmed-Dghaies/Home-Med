package com.example.home_med


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentLoginBinding
import com.example.home_med.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass.
 */
class Register : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        binding.createAccountButton.setOnClickListener { v: View ->
            v.findNavController().navigate(RegisterDirections.actionRegisterToHome2())
        }
        binding.alreadyHaveAccountButton.setOnClickListener { v: View ->
            v.findNavController().navigate(RegisterDirections.actionRegisterToLogin())
        }

        return inflater.inflate(R.layout.fragment_register, container, false)
    }


}
