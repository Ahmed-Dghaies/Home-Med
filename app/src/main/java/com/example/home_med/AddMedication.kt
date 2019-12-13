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
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.home_med.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Add Medication Fragment
 * This fragment handles the adding of medication to the database
 * Each medication contains a name, quantity, expiration date, and type
 *
 * @constructor Creates the fragment bindings for the Add Medication Fragment. Each medication uses name, quantity, expiration date, and type
 *
 * @property db Firebase database instance
 * @property firebaseAuth Authentication for the current user information using the firebase database
 */
class AddMedication : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    lateinit var firebaseAuth: FirebaseAuth

    /**
     * Fragment for creating the view for the AddMedication Fragment which includes the following functionality:
     * - Enter Name
     * - Enter Quantity
     * - Enter Expiration Date
     * - Enter Medication Type
     * - Days used (Active or Inactive)
     *
     * @param inflater Layout inflater used for navigation of application showing the fragment_add_medication
     * @param container Container group used for the bindings
     * @param savedInstanceState The saved instance state of the fragment including the container and inflater
     *
     * @return Returns the root binding
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAddMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

        val binding1: FragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        var medicationType = "Error"
        val medicationDays = arrayListOf("Days")

        firebaseAuth = FirebaseAuth.getInstance()
        var user: FirebaseUser? = firebaseAuth.getCurrentUser()
        val currentUserId = user!!.uid

        var userProfile = user.email.toString()


        binding.saveMedicationButton.setOnClickListener { v: View ->

            if (vm_medicationNameTitle.text.toString().isEmpty()) {
                Toast.makeText(context, "Please Enter a Medication Name", Toast.LENGTH_SHORT).show()
            }
            else if (vm_medicationQtyTitle.text.toString().isEmpty()) {
                Toast.makeText(context, "Please Enter a Medication Quantity", Toast.LENGTH_SHORT).show()
            }
            else if (vm_medicationExpDateTitle.text.toString().isEmpty()) {
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

                val medicine = m_LocalMedication(vm_medicationNameTitle.text.toString(), vm_medicationQtyTitle.text.toString(),
                    medicationType, vm_medicationExpDateTitle.text.toString(), true, medicationDays, userProfile)

                db.collection("Medication")
                    .document(vm_medicationNameTitle.text.toString())
                    .set(medicine)

                val mgr = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mgr.hideSoftInputFromWindow(vm_medicationExpDateTitle.getWindowToken(), 0)
                v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }
}