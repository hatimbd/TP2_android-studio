package com.example.androidtp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
    }

    public fun goToOrders(view: View)
    {
        finish();
    }
}