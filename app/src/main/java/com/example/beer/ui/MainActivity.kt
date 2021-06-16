package com.example.beer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.beer.R

/*
 * Created by Marko 16 June 2021.
 * Main class
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.navigationFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}