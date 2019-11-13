package com.example.home_med


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentLocalMedicationBinding


class LocalMedication : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLocalMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_local_medication, container, false)

        binding.viewMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication())
        }
        binding.addMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToAddMedication())
        }
        binding.homeButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToHome2())
        }
        setHasOptionsMenu(true)
        return binding.root
    }


}
