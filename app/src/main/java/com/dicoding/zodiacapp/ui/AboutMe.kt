package com.dicoding.zodiacapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.zodiacapp.R

class AboutMe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }

}