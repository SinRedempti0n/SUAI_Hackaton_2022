package com.example.hackaton

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.R.attr.path

import android.database.sqlite.SQLiteDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    val USER_KEY: String = "User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textLoad: TextView = findViewById(R.id.textView)

        textLoad.setText(R.string.load_table)

        // TODO: 21.08.2021 SQL Load


        textLoad.setText(R.string.load_done)
        val intent: Intent = Intent(this@MainActivity, RouteActivity::class.java)
        startActivity(intent)
        //finish()
    }
}