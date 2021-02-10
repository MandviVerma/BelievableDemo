package com.believable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.believable.CartFragment.Companion.cartList
import kotlinx.android.synthetic.main.activity_order_confirm.*

class OrderConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        supportActionBar?.hide()
        btn_ok.setOnClickListener {
            cartList.clear()
            finish()
        }
    }
}