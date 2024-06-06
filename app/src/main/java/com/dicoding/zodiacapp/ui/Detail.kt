package com.dicoding.zodiacapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.zodiacapp.R
import com.dicoding.zodiacapp.data.Zodiac

class Detail: AppCompatActivity() {
    private lateinit var photoZodiac: ImageView
    private lateinit var descDetail: TextView
    private lateinit var birthDetail: TextView
    private lateinit var titleDetail: TextView
    private lateinit var zodiacSign: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val scrollview = findViewById<ScrollView>(R.id.parentView)
        scrollview.viewTreeObserver.addOnDrawListener {
            if (scrollview.scrollY>0){
                supportActionBar?.hide()
            }else{
                supportActionBar?.show()
            }
        }

        val dataZodiac = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(ZODIAC_KEY, Zodiac::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(ZODIAC_KEY)
        }

        supportActionBar?.title = dataZodiac?.name

        photoZodiac = findViewById(R.id.imv_zodiac_detail)
        descDetail = findViewById(R.id.tv_desc_detail_zodiac)
        birthDetail = findViewById(R.id.tv_date_zodiac_detail)
        zodiacSign = findViewById(R.id.tv_zodiac_lambang)
        titleDetail = findViewById(R.id.tv_title_detail_zodiac)

        titleDetail.text = dataZodiac?.name
        descDetail.text = dataZodiac?.description
        birthDetail.text = dataZodiac?.birthdate
        zodiacSign.text = dataZodiac?.sign
        dataZodiac?.img?.let { photoZodiac.setImageResource(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_data))
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
            }
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ZODIAC_KEY = "key_zodiac"
    }
}