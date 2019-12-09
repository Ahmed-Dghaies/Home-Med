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


class EditUserInfo : Fragment() {

    lateinit var firebaseAuth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

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
                if (document != null) {
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
