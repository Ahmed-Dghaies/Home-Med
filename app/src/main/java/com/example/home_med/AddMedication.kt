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
import androidx.core.content.ContextCompat.getSystemService




class AddMedication : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAddMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

        binding.saveMedicationButton.setOnClickListener { v: View ->
            val medicationStatusCheck: Boolean = activeButton.isActivated

            val medicine = m_LocalMedication(medicationName.text.toString(), medicationQty.text.toString(),
                medicationType.text.toString(), medicationExpDate.text.toString(), medicationStatusCheck)

            db.collection("Medication")
                .document(medicationName.text.toString())
                .set(medicine)

            val mgr = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mgr.hideSoftInputFromWindow(medicationType.getWindowToken(), 0)
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}
