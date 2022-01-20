package com.example.usbankdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.usbankdemo.databinding.ActivityMainBinding
import com.example.usbankdemo.ui.album.AlbumFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val view=binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        openFragment(AlbumFragment.newInstance())
    }

    fun openFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}