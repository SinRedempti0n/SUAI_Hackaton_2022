package com.example.hackaton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textLoad: TextView = findViewById(R.id.textView)

        textLoad.setText(R.string.load_table)
        // TODO: 21.08.2021 SQL Load

        textLoad.setText(R.string.load_done)
        val intent: Intent = Intent(this@MainActivity, RouteActivity::class.java)
        startActivity(intent)
    }
}