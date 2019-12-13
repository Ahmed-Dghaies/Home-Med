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
 * The home fragment is a menu fragment which allows the users to select their local medications as well as profile information
 * Local medications takes the user to the local medications fragment
 * Profile takes the user to the users profile information fragment
 *
 * @constructor Creates the view for the home fragment using the fragment_home.xml
 */
class Home : Fragment() {

    /**
     * Home fragment view instance
     * The home fragment contains the local medications button and the profile button
     * Local Medications - Takes the user to the local medications fragment
     * Profile - Takes the user to the users profile information fragment
     *
     * @param inflater The current inflater of the application showing the home view fragment
     * @param container Contains the view of the fragment_home.xml file
     * @param savedInstanceState The saved instance state of both the container and the inflater
     *
     * @return Returns the root binding
     */
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