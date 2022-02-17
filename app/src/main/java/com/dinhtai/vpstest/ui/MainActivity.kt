package com.dinhtai.vpstest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.dinhtai.vpstest.R
import com.dinhtai.vpstest.databinding.ActivityMainBinding
import com.dinhtai.vpstest.ui.home.HomeFragment
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window= getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.nothing))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabBar()
        binding.bottomNavBar.setItemSelected(R.id.home)
    }

    private fun setUpTabBar() {
        binding.bottomNavBar.setOnItemSelectedListener {
            when (it) {
                R.id.home -> {
                    openHomeFragment()
                }
            }
        }

    }

    private fun openHomeFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.layout_content, HomeFragment())
            .commitAllowingStateLoss()
    }
}