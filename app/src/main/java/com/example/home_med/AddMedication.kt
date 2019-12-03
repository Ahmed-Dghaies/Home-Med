package com.example.home_med

import android.content.Context
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
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService




class AddMedication : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAddMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

        var medicationType = "Error"
        val medicationDays = arrayListOf("Days")


        binding.saveMedicationButton.setOnClickListener { v: View ->

            if (medicationName.text.toString().isEmpty()) {
                Toast.makeText(context, "Please Enter a Medication Name", Toast.LENGTH_SHORT).show()
            }
            else if (medicationQty.text.toString().isEmpty()) {
                Toast.makeText(context, "Please Enter a Medication Quantity", Toast.LENGTH_SHORT).show()
            }
            else if (medicationExpDate.text.toString().isEmpty()) {
                Toast.makeText(context, "Please Enter a Medication Expiration Date", Toast.LENGTH_SHORT).show()
            }
            else if (!pillButton.isChecked && !liquidButton.isChecked) {
                Toast.makeText(context, "Please Select a Medication Type", Toast.LENGTH_SHORT).show()
            }
            else {

                if (sundayCheck.isChecked) {
                    medicationDays.add(sundayCheck.text.toString())
                }
                if (mondayCheck.isChecked) {
                    medicationDays.add(mondayCheck.text.toString())
                }
                if (tuesdayCheck.isChecked) {
                    medicationDays.add(tuesdayCheck.text.toString())
                }
                if (wednesdayCheck.isChecked) {
                    medicationDays.add(wednesdayCheck.text.toString())
                }
                if (thursdayCheck.isChecked) {
                    medicationDays.add(thursdayCheck.text.toString())
                }
                if (fridayCheck.isChecked) {
                    medicationDays.add(fridayCheck.text.toString())
                }
                if (saturdayCheck.isChecked) {
                    medicationDays.add(saturdayCheck.text.toString())
                }

                if (pillButton.isChecked) {
                    medicationType = pillButton.text.toString()
                }
                else if (liquidButton.isChecked) {
                    medicationType = liquidButton.text.toString()
                }

                val medicine = m_LocalMedication(medicationName.text.toString(), medicationQty.text.toString(),
                    medicationType, medicationExpDate.text.toString(), true, medicationDays)

                db.collection("Medication")
                    .document(medicationName.text.toString())
                    .set(medicine)

                val mgr = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mgr.hideSoftInputFromWindow(medicationExpDate.getWindowToken(), 0)
                v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}