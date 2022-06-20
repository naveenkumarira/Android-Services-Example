package com.example.servicesexample

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.servicesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Added a new line
        binding.backgroundServiceButton.setOnClickListener {
            //Launch Background services
            startService(Intent(this, SimpleBackgroundService::class.java))
        }
        binding.foregroundServiceButton.setOnClickListener {
            //Launch Foreground services
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(Intent(this, SimpleForegroundService::class.java))
            }
        }
    }
}