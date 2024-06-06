package com.dicoding.zodiacapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.zodiacapp.R
import com.dicoding.zodiacapp.adapter.ListZodiac
import com.dicoding.zodiacapp.data.Zodiac

class MainActivity : AppCompatActivity() {
    private lateinit var rvData: RecyclerView
    private lateinit var toolbarMain: Toolbar
    private val listData = ArrayList<Zodiac>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.topbar_main)
        setSupportActionBar(toolbarMain)

        rvData = findViewById(R.id.rv_item_zodiac)
        rvData.setHasFixedSize(true)

        listData.addAll(getListZodiac())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_about -> {
                val intentActivity = Intent(this@MainActivity, AboutMe::class.java)
                startActivity(intentActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListZodiac(): ArrayList<Zodiac> {
        val dataName = resources.getStringArray(R.array.zodiac_name)
        val dataDescription = resources.getStringArray(R.array.zodiac_description)
        val dataPhoto = resources.obtainTypedArray(R.array.zodiac_photo)
        val dataBirthdate = resources.getStringArray(R.array.zodiac_range_birthdate)
        val dataSignZodiac = resources.getStringArray(R.array.lambang_zodiac)

        val listZodiac = ArrayList<Zodiac>()
        for (i in dataName.indices) {
            val zodiac = Zodiac(dataName[i],  dataPhoto.getResourceId(i, -1),
                dataBirthdate[i], dataDescription[i], dataSignZodiac[i])

            listZodiac.add(zodiac)
        }
        dataPhoto.recycle()
        return listZodiac
    }

    private fun showRecyclerList() {
        rvData.layoutManager = LinearLayoutManager(this)
        val zodiacAdapter = ListZodiac(listData)
        rvData.adapter = zodiacAdapter

        rvData.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}