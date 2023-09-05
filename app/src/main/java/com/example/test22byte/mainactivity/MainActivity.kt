package com.example.test22byte.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test22byte.R
import com.example.test22byte.databinding.ActivityMainBinding
import com.example.test22byte.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }
}