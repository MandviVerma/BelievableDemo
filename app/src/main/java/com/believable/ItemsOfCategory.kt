package com.believable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_items_of_category.*

class ItemsOfCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_of_category)
         supportActionBar?.hide()
        val item = intent.getStringExtra("CategoryName") ?: ""
        val image = intent.getStringExtra("CategoryImage") ?: ""

        tvItem.text = item
        Glide.with(this)
            .load(image)
            .centerCrop()
            .placeholder(android.R.drawable.ic_media_rew)
            .into(ivItem);
    }
}