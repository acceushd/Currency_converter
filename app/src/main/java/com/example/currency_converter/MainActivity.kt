package com.example.currency_converter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var currency = Currency()
    private lateinit var converted: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        converted = findViewById(R.id.ConvertedCurrency)
        val selectCurrencyButton: Button = findViewById(R.id.selectCurrencyButton)
        val amountView = findViewById<EditText>(R.id.amount)
        selectCurrencyButton.setOnClickListener {
            val bottomsheet = CurrencyBottomSheetDialogFragment()
            bottomsheet.setOnCurrencySelectedListener { selectedCurrency ->
                lifecycleScope.launch {
                    val amount = getNumber(amountView)
                    currency = selectedCurrency
                    converted.text = "Converted ${converter(currency.code, amount = amount)}"
                }
                Toast.makeText(this, "Selected: ${selectedCurrency.name}", Toast.LENGTH_SHORT)
                    .show()
            }
            bottomsheet.show(supportFragmentManager, "currencyBottomSheet")
        }
    }

    private fun getNumber(editText: EditText): Int {
        val numberString = editText.text.toString()
        return try {
            numberString.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

}