package com.example.my_game

import  android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Random

enum class Level {
    EASY,
    MEDIUM,
    HARD
}

class NewGame : AppCompatActivity() {

    private var randomNumber: Int = 0
    private var attempts: Int = 0
    private var score: Int = 0
    private lateinit var currentLevel: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        val button1: Button = findViewById(R.id.guessNumber)
        val scoreText: TextView = findViewById(R.id.score)
        val highScore: TextView = findViewById(R.id.highscore)

        val levelName = intent.getStringExtra("level")
        currentLevel = Level.valueOf(levelName ?: Level.EASY.name)

        when (currentLevel) {
            Level.EASY -> randomNumber = generateRandomNumber(1, 10)
            Level.MEDIUM -> randomNumber = generateRandomNumber(1, 50)
            Level.HARD -> randomNumber = generateRandomNumber(1, 100)
        }

        highScore.text = loadHighScore(currentLevel).toString()

        button1.setOnClickListener {
            val text1: TextView = findViewById(R.id.userInput)
            val userInput = text1.text.toString()
            val userGuess = userInput.toIntOrNull()
            val text2: TextView = findViewById(R.id.displayMessage)
            val correctNumb: TextView = findViewById(R.id.result)

            correctNumb.text = ""

            if (userGuess != null) {
                attempts += 1

                when (currentLevel) {
                    Level.EASY -> {
                        if (userGuess == randomNumber) {
                            score += 2 // Award 2 points for a correct guess
                            text2.setText("Genius")
                            scoreText.setText("$score")
                            correctNumb.setText("$randomNumber")
                            attempts = 0
                            randomNumber = generateRandomNumber(1, 10)

                            saveHighScore(currentLevel, score) // Save the high score
                            highScore.text = loadHighScore(currentLevel).toString() // Load the high score
                        } else {
                            if (attempts == 3) {
                                text2.setText("Better Luck! Try Again")
                                attempts = 0
                                correctNumb.setText("$randomNumber")
                            } else {
                                if (userGuess < randomNumber) {
                                    text2.setText("Heat!")
                                } else {
                                    text2.setText("Cool!")
                                }
                            }
                        }
                    }

                    Level.MEDIUM -> {
                        // Handle medium level rules
                        if (userGuess == randomNumber) {
                            if (attempts == 1) {
                                score += 2
                            } else {
                                score += 1
                            }
                            text2.setText("Genius")
                            scoreText.setText("$score")
                            correctNumb.setText("$randomNumber")
                            attempts = 0
                            randomNumber = generateRandomNumber(1, 50)
                        } else {
                            if (attempts == 3) {
                                score -= 2
                                text2.setText("Better Luck! Try Again")
                                attempts = 0
                                correctNumb.setText("$randomNumber")
                                randomNumber = generateRandomNumber(1, 50)

                                saveHighScore(currentLevel, score) // Save the high score
                                highScore.text = loadHighScore(currentLevel).toString() // Load the high score
                            } else if (Math.abs(userGuess - randomNumber) <= 10) {
                                text2.setText("Colse")
                                if (userGuess < randomNumber) {
                                    text2.append("\nHeat!")
                                } else {
                                    text2.append("\nCool!")
                                }
                            } else {
                                if (userGuess < randomNumber) {
                                    text2.setText("Heat!")
                                } else {
                                    text2.setText("Cool!")
                                }
                            }
                        }
                    }

                    Level.HARD -> {
                        // Handle hard level rules
                        if (userGuess == randomNumber) {
                            if (attempts == 1) {
                                score += 2
                            } else {
                                score += 1
                            }
                            text2.setText("Genius")
                            scoreText.setText("$score")
                            correctNumb.setText("$randomNumber")
                            attempts = 0
                            randomNumber = generateRandomNumber(1, 100)

                            saveHighScore(currentLevel, score) // Save the high score
                            highScore.text = loadHighScore(currentLevel).toString() // Load the high score
                        } else {
                            if (attempts == 3) {
                                score -= 2
                                text2.setText("Better Luck! Try Again")
                                attempts = 0
                                correctNumb.setText("$randomNumber")
                                randomNumber = generateRandomNumber(1, 100)
                            } else if (Math.abs(userGuess - randomNumber) <= 8) {
                                text2.setText("Close")
                                if (userGuess < randomNumber) {
                                    text2.append("\nHeat!")
                                } else {
                                    text2.append("\nCool!")
                                }
                            } else {
                                if (userGuess < randomNumber) {
                                    text2.setText("Heat!")
                                } else {
                                    text2.setText("Cool!")
                                }
                            }
                        }
                    }
                }
            } else {
                text2.setText("Please enter a valid number.")
            }
        }
    }

    private fun generateRandomNumber(min: Int, max: Int): Int {
        return Random().nextInt(max - min + 1) + min
    }

    private fun saveHighScore(level: Level, score: Int) {
        val sharedPreferences = getSharedPreferences("MyGamePreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val highScore = sharedPreferences.getInt(level.name, 0)
        if (score > highScore) {
            editor.putInt(level.name, score)
            editor.apply()
        }
    }

    private fun loadHighScore(level: Level): Int {
        val sharedPreferences = getSharedPreferences("MyGamePreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(level.name, 0)
    }
}
