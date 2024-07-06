package com.example.my_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class Levels : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        val easyButton: Button = findViewById(R.id.easy)
        easyButton.setOnClickListener {
            val intent = Intent(this, NewGame::class.java)
            intent.putExtra("level", Level.EASY.name)
            startActivity(intent)
        }

        val mediumButton: Button = findViewById(R.id.medium)
        mediumButton.setOnClickListener {
            val intent = Intent(this, NewGame::class.java)
            intent.putExtra("level", Level.MEDIUM.name)
            startActivity(intent)
        }

        val hardButton: Button = findViewById(R.id.hard)
        hardButton.setOnClickListener {
            val intent = Intent(this, NewGame::class.java)
            intent.putExtra("level", Level.HARD.name)
            startActivity(intent)
        }

    }
}