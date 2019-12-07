package com.example.home_med

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.home_med.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.localMedicationsButton.setOnClickListener { v: View ->
            v.findNavController().navigate(HomeDirections.actionHome2ToLocalMedication())
        }
        binding.profileButton.setOnClickListener { v: View ->
            v.findNavController().navigate(HomeDirections.actionHome2ToProfileFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }


}