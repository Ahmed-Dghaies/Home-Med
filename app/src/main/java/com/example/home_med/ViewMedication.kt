package com.example.home_med

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentViewMedicationBinding
import com.google.firebase.firestore.FirebaseFirestore

/**
 * View Medication Fragment
 * Retrieves the information from the database for all medication information including name, quantity, days used, expriation, active/inactive, type
 *
 * @constructor Creates the view for each medication that contains the medication name, quantity, type
 *
 * @property db The firebase database that is used for profile information
 */
class ViewMedication : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    /**
     * Fragment view for the fragment_view_medication.xml that is inserted within the local_medication fragment
     *
     * @param inflater Layout inflater used for navigation of application showing the fragment_view_medication
     * @param container Container group used for the bindings
     * @param savedInstanceState The saved instance state of the fragment including the container and inflater
     *
     * @return Returns the root binding
     */
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

                if (medicationStatus) {
                    binding.activateMedicationBtn.visibility = View.INVISIBLE
                    binding.deactivateMedicationBtn.visibility = View.VISIBLE
                }
                else if (!medicationStatus) {
                    binding.deactivateMedicationBtn.visibility = View.INVISIBLE
                    binding.activateMedicationBtn.visibility = View.VISIBLE
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
        binding.deactivateMedicationBtn.setOnClickListener { v: View ->
            medicationDocRef.update("m_medicationStatus", false)
            v.findNavController().navigate(ViewMedicationDirections.actionViewMedicationToLocalMedication())
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}