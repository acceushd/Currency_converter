package com.example.currency_converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CurrencyBottomSheetDialogFragment :
    BottomSheetDialogFragment() {
    private lateinit var onCurrencySelected: (Currency) -> Unit

    fun setOnCurrencySelectedListener(listener: (Currency) -> Unit) {
        onCurrencySelected = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.currency_bottom_sheet, container, false)
        val recyclerView: RecyclerView =
            view.findViewById(R.id.currencyRecyclerView)
        val currencies = listOf(
            Currency("AUD", "Australian Dollar"),
            Currency("CAD", "Canadian Dollar"),
            Currency("CHF", "Swiss Franc"),
            Currency("CNY", "Chinese Yuan"),
            Currency("EUR", "Euro"),
            Currency("GBP", "British Pound"),
            Currency("HKD", "Hong Kong Dollar"),
            Currency("INR", "Indian Rupee"),
            Currency("JPY", "Japanese Yen"),
            Currency("NZD", "New Zealand Dollar"),
            Currency("SGD", "Singapore Dollar"),
            Currency("USD", "US Dollar"),
            Currency("ZAR", "South African Rand"))
        val adapter = CurrencyListAdapter(currencies) { selectedCurrency:Currency ->
            onCurrencySelected(selectedCurrency)
            dismiss() // Close the bottom sheet after selection
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }
}