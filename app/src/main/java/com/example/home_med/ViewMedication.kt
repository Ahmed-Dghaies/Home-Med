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


class ViewMedication : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentViewMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_medication, container, false)

        binding.deleteMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(ViewMedicationDirections.actionViewMedicationToLocalMedication())
        }
        binding.goBackToLocalMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(ViewMedicationDirections.actionViewMedicationToLocalMedication())
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}
