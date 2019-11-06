package com.example.home_med

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.arch.core.executor.DefaultTaskExecutor
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentAddMedicationBinding
import com.example.home_med.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_medication.*
import java.util.*


class AddMedication : Fragment() {

    data class Medicine(
        var name: String,
        var qty : Int,
        var expDate : String
    )

    lateinit var firebaseDatabase : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("EDMT_FIREBASE")


        val binding: FragmentAddMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

        binding.saveMedicationButton.setOnClickListener { v: View ->
            addMedicine(medicationName.text.toString(), medicationQty.text.toString().toInt(), expDate.text.toString())
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
        }
        binding.deleteMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    fun addMedicine(name : String, qty : Int, expDate : String) {
        var med = Medicine(name, qty, expDate)
        databaseReference.push()
            .setValue(med)
    }
}
