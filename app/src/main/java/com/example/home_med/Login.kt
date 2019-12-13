package com.example.home_med

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Login Fragment
 * Allows the user to enter their email and password (Once already created) to log into their account and continue to the rest of the application
 * Each user must have already registered their email address
 * Each user much have already created a password associated with their email address
 *
 * @constructor Creates the fragment for the fragment_login.xml file
 *
 * @property binding The binding used for the firebase database
 * @property firebaseAuth Authentication for the user to be able to log into the application
 */
class Login : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    /**
     * Creates the fragment for the fragment_login.xml file
     * Once the fragment has been started, the user is allowed to log into the fragment
     *
     * @param inflater Layout inflater used for navigation of application showing the fragment_login
     * @param container Container group used for the bindings
     * @param savedInstanceState The saved instance state of the fragment including the container and inflater
     *
     * @return Returns the root binding
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener { v: View ->
            loginUser()
            val mgr = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mgr.hideSoftInputFromWindow(loginButton.getWindowToken(), 0)
        }
        binding.loginPageText.setOnClickListener { v: View ->
            binding.loginButton.findNavController().navigate(LoginDirections.actionLoginToRegister())
        }

        val user: FirebaseUser? = firebaseAuth.currentUser
        if (user != null) {
            container?.findNavController()?.navigate(LoginDirections.actionLoginToHome2())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Logs the user into the correct account when given an appropriate email address and password that have already been registered
     * Once the user logs into the account they can access the rest of the local medication and profile functionality
     */
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