package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val counterButton = findViewById<Button>(R.id.button)
        val resetButton = findViewById<Button>(R.id.button2)
        val textView = findViewById<TextView>(R.id.textView)

        // Shared Preferences


        val preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Load saved counter data
        counter = preferences.getInt("counter", 0)
        textView.text = counter.toString()

        // Counter button
        counterButton.setOnClickListener {
            counter++

            textView.text = counter.toString()

            val editor = preferences.edit()
            editor.putInt("counter", counter)
            editor.apply()
        }

        // Reset button
        resetButton.setOnClickListener {
            counter = 0

            textView.text = counter.toString()

            val editor = preferences.edit()
            editor.putInt("counter", counter)
            editor.apply()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }
}