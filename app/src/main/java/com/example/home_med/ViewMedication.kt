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
import com.example.home_med.databinding.FragmentRegisterBinding
import com.example.home_med.databinding.FragmentViewMedicationBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_view_medication.*


class ViewMedication : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentViewMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_medication, container, false)
        val args = ViewMedicationArgs.fromBundle(arguments!!)
        val medicationDocRef = db.collection("Medication").document(args.medicationName)

        medicationDocRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val medicationName = documentSnapshot.getString("m_medicationName")
                val medicationExpDate = documentSnapshot.getString("m_medicationExpDate")
                val medicationQty = documentSnapshot.getString("m_medicationQty")
                val medicationType = documentSnapshot.getString("m_medicationType")
                val medicationStatus = documentSnapshot.getBoolean("m_medicationStatus")!!

                if (medicationStatus == true) {
                    binding.activateMedicationBtn.visibility = View.INVISIBLE
                }
                if (medicationType == "Pill") {
                    binding.pillButton.isChecked = true
                }
                else {
                    binding.liquidButton.isChecked = true
                }

                binding.vmMedicationNameTitle.setText(medicationName)
                binding.vmMedicationExpDateTitle.setText(medicationExpDate)
                binding.vmMedicationQtyTitle.setText(medicationQty)


            }

        binding.deleteMedicationButton.setOnClickListener { v: View ->
            db.collection("Medication")
                .document(args.medicationName).delete()
            v.findNavController().navigate(ViewMedicationDirections.actionViewMedicationToLocalMedication())
        }
        binding.activateMedicationBtn.setOnClickListener { v: View ->
            medicationDocRef.update("m_medicationStatus", true)
            v.findNavController().navigate(ViewMedicationDirections.actionViewMedicationToLocalMedication())
        }
        binding.updateMedicationBtn.setOnClickListener { v: View ->
            medicationDocRef.update("m_medicationExpDate", binding.vmMedicationExpDateTitle.text.toString())
            medicationDocRef.update("m_medicationQty", binding.vmMedicationQtyTitle.text.toString())

            if (binding.pillButton.isChecked) {
                medicationDocRef.update("m_medicationType", "Pill")
            }
            else {
                medicationDocRef.update("m_medicationType", "Liquid")
            }

            v.findNavController().navigate(ViewMedicationDirections.actionViewMedicationToLocalMedication())
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}