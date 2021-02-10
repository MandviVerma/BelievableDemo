package com.believable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_checkout.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        supportActionBar?.hide()
        btnCheckOut.setOnClickListener {
            val intent = Intent(
                this,
                OrderConfirmActivity::class.java
            )

            startActivity(intent)
            finish()
        }

    }
}