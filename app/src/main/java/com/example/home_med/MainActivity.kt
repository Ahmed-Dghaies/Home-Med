package com.example.home_med

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.home_med.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Main Activity Fragment
 * The main activity fragment is used to host each fragment in the application
 * Stores the instance state from each other fragment and is used to navigate throughout the application
 *
 * @constructor Loads the instance state of a given fragment to be used
 */
class MainActivity : AppCompatActivity() {

    /**
     * Creates the activity_main.xml view once the instance state from another fragment has been saved
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //creates the data binding variable
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //creates the navController object to help navigate through app
        val navController = this.findNavController(R.id.myNavHostFragment)
        //sets up the action bar that is used on the top of the app
        NavigationUI.setupActionBarWithNavController(this, navController)

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    /**
     * Navigation controller that uses the myNavHostFragment to allow the application to enter and exit other fragments
     *
     * @return Returns the navigation controller
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}