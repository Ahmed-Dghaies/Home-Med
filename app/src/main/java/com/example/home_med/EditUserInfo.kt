package com.example.home_med

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentEditUserInfoBinding
import com.example.home_med.databinding.FragmentHomeBinding
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import com.example.home_med.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Edit User Information Fragment
 * Fragment is used to alter the user information that is stored in the firebase database
 * Information that can be altered includes User first name, last name, and age
 *
 * @constructor Creates the fragment bindings for the Edit User Information Fragment. Each user can adjust first name, last name, and age
 *
 * @property firebaseAuth Authentication for the current user information using the firebase database
 * @property db Firebase database instance
 */
class EditUserInfo : Fragment() {

    lateinit var firebaseAuth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    /**
     * Fragment for editing the user information
     * Authorized users are able to access their current information and alter the content
     * Once the user has been authenticated, then the user may select to change their first name, last name, and age
     * The data is taken from the inputs and then altered in the firebase database
     *
     * @param inflater Layout inflater used for navigation of application showing the fragment_edit_user_info
     * @param container Container group used for the bindings
     * @param savedInstanceState The saved instance state of the fragment including the container and inflater
     *
     * @return Returns the root binding
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentEditUserInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_user_info, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = firebaseAuth.getCurrentUser()
        val currentUserId = user!!.uid

        val docRef = db.collection("UserData").document(currentUserId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    Log.d("MyTag", "DocumentSnapshot data: ${document.data}")
                    binding.userFirstName.setText(document.data!!["first_name"].toString())
                    binding.userLastName.setText(document.data!!["last_name"].toString())
                    binding.userAge.setText(document.data!!["age"].toString())
                } else {
                    Log.d("MyTag", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("MyTag", "get failed with ", exception)
            }

        binding.backButton.setOnClickListener { v: View ->
            v.findNavController().navigate(EditUserInfoDirections.actionEditUserInfoToProfileFragment())
        }

        binding.save.setOnClickListener { v: View ->
            val user_data = User(currentUserId, binding.userFirstName.text.toString(), binding.userLastName.text.toString(), Integer.parseInt(binding.userAge.text.toString()), user.email!!)

            db.collection("UserData")
                .document(currentUserId)
                .set(user_data)
            v.findNavController().navigate(EditUserInfoDirections.actionEditUserInfoToProfileFragment())
        }

        return binding.root
    }



}
