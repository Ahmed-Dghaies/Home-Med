package com.example.home_med

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentAddMedicationBinding
import com.example.home_med.models.m_LocalMedication
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_medication.*


class AddMedication : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAddMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

        binding.saveMedicationButton.setOnClickListener { v: View ->
            var medicationStatusCheck = true
            if (activeButton.isActivated) {
                medicationStatusCheck = true
            }
            else if (inactiveButton.isActivated) {
                medicationStatusCheck = false
            }

            val medicine = m_LocalMedication(medicationName.text.toString(), medicationQty.text.toString(),
                medicationType.text.toString(), medicationExpDate.text.toString(), medicationStatusCheck)

            db.collection("Medication")
                .document(medicationName.text.toString())
                .set(medicine)
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}
