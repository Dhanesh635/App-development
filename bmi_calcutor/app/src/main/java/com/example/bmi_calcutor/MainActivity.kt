package com.example.bmi_calcutor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmi_calcutor.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the calculate button
        binding.calculateBtn.setOnClickListener {
            calculateBMI()
        }

        // Handle edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculateBMI() {
        val weight = binding.weightEdit.text.toString().toFloatOrNull()
        val height = binding.heightEdit.text.toString().toFloatOrNull()

        if (weight != null && height != null && height > 0) {
            // Calculate BMI
            val bmi = weight / (height / 100).pow(2)
            val bmiResult = String.format("%.2f", bmi)

            // Determine BMI category
            val bmiCategory = when {
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal weight"
                bmi < 30 -> "Overweight"
                else -> "Obese"
            }

            // Display the result
            binding.resultText.text = "BMI: $bmiResult\nCategory: $bmiCategory"
        } else {
            // Handle invalid input
            binding.resultText.text = "Invalid Input"
        }
    }
}
