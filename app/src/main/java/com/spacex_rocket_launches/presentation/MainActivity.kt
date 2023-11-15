package com.spacex_rocket_launches.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spacex_rocket_launches.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}