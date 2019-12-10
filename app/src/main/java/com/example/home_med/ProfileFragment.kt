package com.example.home_med

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentProfileBinding
import com.example.home_med.models.User
import com.example.home_med.models.m_LocalMedication
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var firebaseAuth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        var user: FirebaseUser? = firebaseAuth.getCurrentUser()
        val currentUserId = user!!.uid


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
        binding.editInfo.setOnClickListener { v: View ->
            v.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditUserInfo())
        }

        val docRef = db.collection("UserData").document(currentUserId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    Log.d("MyTag", "DocumentSnapshot data: ${document.data}")
                    binding.userFirstName.text = document.data!!["first_name"].toString()
                    binding.userLastName.text = document.data!!["last_name"].toString()
                    binding.userAge.text = document.data!!["age"].toString()
                } else {
                    Log.d("MyTag", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("MyTag", "get failed with ", exception)
            }

        return binding.root
    }
}