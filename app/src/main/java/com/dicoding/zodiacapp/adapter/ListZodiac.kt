package com.dicoding.zodiacapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.zodiacapp.R
import com.dicoding.zodiacapp.data.Zodiac
import com.dicoding.zodiacapp.ui.Detail

class ListZodiac(private val listZodiac: ArrayList<Zodiac>): RecyclerView.Adapter<ListZodiac.ZodiacViewHolder>() {

    class ZodiacViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgZodiac: ImageView = itemView.findViewById(R.id.img_zodiac_item)
        val tvName: TextView = itemView.findViewById(R.id.tv_zodiac_name)
        val tvRangeDate: TextView = itemView.findViewById(R.id.tv_zodiac_date_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_zodiac, parent, false)
        return ZodiacViewHolder(view)
    }

    override fun getItemCount(): Int = listZodiac.size

    override fun onBindViewHolder(holder: ZodiacViewHolder, position: Int) {
        val (name, image, birthZodiac) = listZodiac[position]

        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.imgZodiac)
        holder.tvName.text = name
        holder.tvRangeDate.text = birthZodiac

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, Detail::class.java)
            intentDetail.putExtra(KEY_ZODIAC, listZodiac[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    companion object {
        const val KEY_ZODIAC = "key_zodiac"
    }
}