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
import com.example.home_med.databinding.FragmentAddMedicationBinding
import com.example.home_med.databinding.FragmentHomeBinding


class AddMedication : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentAddMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

        binding.saveMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
        }
        binding.deleteMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(AddMedicationDirections.actionAddMedicationToLocalMedication())
        }
        return inflater.inflate(R.layout.fragment_add_medication, container, false)
    }


}
