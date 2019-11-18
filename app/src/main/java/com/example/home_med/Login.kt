package com.example.home_med

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.fragment.app.FragmentTransaction


class Login : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener { v: View ->
            loginUser()
        }
        binding.loginPageText.setOnClickListener { v: View ->
            binding.loginButton.findNavController().navigate(LoginDirections.actionLoginToRegister())
        }

        var user: FirebaseUser? = firebaseAuth.currentUser
        if (user != null) {
            container?.findNavController()?.navigate(LoginDirections.actionLoginToHome2())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun loginUser() {
        val email: String = binding.editTextEmail.text.toString()
        val password: String = binding.editTextPassword.text.toString()
        when {
            email.isEmpty() -> binding.editTextEmail.error = "this field can't be empty"
            password.isEmpty() -> binding.editTextPassword.error = "this field can't be empty"

            else -> firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity!!) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = firebaseAuth.currentUser
                        view!!.findNavController().navigate(LoginDirections.actionLoginToHome2())

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        binding.errorText.text = "Wrong email or password, try again please"
                        binding.editTextEmail.setText("")
                        binding.editTextPassword.setText("")
                        binding.errorText.visibility = View.VISIBLE
                    }
                }
        }
    }

}
