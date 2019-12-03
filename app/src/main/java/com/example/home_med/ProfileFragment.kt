package com.example.home_med

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        var user: FirebaseUser? = firebaseAuth.getCurrentUser()


        if (user == null) {
            view!!.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLogin())
        } else {
            binding.userEmail.text = user.email
        }

        binding.backButton.setOnClickListener { v: View ->
            v.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToHome2())
        }
        binding.logoutButton.setOnClickListener { v: View ->
            firebaseAuth.signOut()
            v.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLogin())
        }
        return binding.root
    }
}