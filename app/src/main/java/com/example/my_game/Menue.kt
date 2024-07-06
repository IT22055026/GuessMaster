package com.example.my_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class Menue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menue)

        val button: Button = findViewById(R.id.newGame)

        val button1: Button = findViewById(R.id.level)

        val button2: Button = findViewById(R.id.readMe)

        button.setOnClickListener {
            val intent = Intent(this, NewGame::class.java)
            startActivity(intent)
        }

        button1.setOnClickListener {
            val intent = Intent(this, Levels::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, ReadMe::class.java)
            startActivity(intent)
        }
    }
}