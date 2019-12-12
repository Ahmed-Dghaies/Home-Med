package com.example.home_med


import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_add_medication.*
import kotlinx.android.synthetic.main.fragment_register.*


/**
 * A simple [Fragment] subclass.
 */
class Register : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener { v: View ->
            registerUser()
            val mgr = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mgr.hideSoftInputFromWindow(registerButton.getWindowToken(), 0)
        }
        binding.registerPageText.setOnClickListener { v: View ->
            v.findNavController().navigate(RegisterDirections.actionRegisterToLogin())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun registerUser() {
        val email: String = binding.editTextEmail.text.toString()
        val password: String = binding.editTextPassword.text.toString()
        val passwordConfirmation = binding.editTextPasswordConfirmation.text.toString()
        when {
            email.isEmpty() -> binding.editTextEmail.error = "This field can't be empty"
            password.isEmpty() -> binding.editTextPassword.error = "This field can't be empty"
            password.length < 6 -> binding.editTextPassword.error = "Password length should not be less than 6"
            !Patterns.EMAIL_ADDRESS.toRegex().matches(email) -> binding.editTextEmail.error = "This is not a valid email address"
            passwordConfirmation.isEmpty() -> binding.editTextPasswordConfirmation.error = "This field can't be empty"
            password != passwordConfirmation -> binding.editTextPasswordConfirmation.error = "Password confirmation is different from password"
            else -> firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity!!) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = firebaseAuth.currentUser
                        view!!.findNavController().navigate(RegisterDirections.actionRegisterToHome2())

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }




}