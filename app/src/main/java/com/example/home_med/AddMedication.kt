package com.example.home_med

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_medication.*
import java.util.*


class AddMedication : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAddMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

        binding.saveMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())

            val medicine = hashMapOf(
                "medicineName" to medicationName.text.toString(),
                "medicineQty" to medicationQty.text.toString().toInt(),
                "medicationType" to medicationType.text.toString(),
                "medicationStatus" to true
            )

            db.collection("Medication")
                .add(medicine)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
        binding.deleteMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}
