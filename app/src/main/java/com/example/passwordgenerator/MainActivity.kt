package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.passwordgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonResult.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.button_result) {
            if (binding.edtLength.text.toString().trim().isNotEmpty()) {

                val lengthInput: Int = Integer.parseInt(binding.edtLength.text.toString())

                // Declare charSet
                val alphaSet = ('A'..'Z') + ('a'..'z')
                val numSet = ('0'..'9')
                val symSet = "!@#$^&*:;<>,.?"

                when {
                    binding.cbSymbol.isChecked && binding.cbNumber.isChecked && binding.cbAlphabetic.isChecked -> generateRandomString(lengthInput, (alphaSet + numSet + symSet).joinToString(""))

                    // 2 checked
                    binding.cbSymbol.isChecked && binding.cbNumber.isChecked -> generateRandomString(lengthInput, (numSet + symSet).joinToString(""))
                    binding.cbNumber.isChecked && binding.cbAlphabetic.isChecked -> generateRandomString(lengthInput, (alphaSet + numSet).joinToString(""))
                    binding.cbSymbol.isChecked && binding.cbAlphabetic.isChecked -> generateRandomString(lengthInput, (alphaSet + symSet).joinToString(""))

                    // 1 checked
                    binding.cbAlphabetic.isChecked -> generateRandomString(lengthInput, (alphaSet).joinToString(""))
                    binding.cbNumber.isChecked -> generateRandomString(lengthInput, (numSet).joinToString(""))
                    binding.cbSymbol.isChecked -> generateRandomString(lengthInput, symSet)
                }
            } else {
                binding.edtLength.error = "This field can't be empty"
            }
        }
    }

    private fun generateRandomString(length: Int, charSet: String) {
        binding.edtResult.setText((1..length).map { charSet.random() }.joinToString(""))
    }
}