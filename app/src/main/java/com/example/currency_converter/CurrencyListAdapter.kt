package com.example.currency_converter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyListAdapter(
    private val currencies: List<Currency>,
    private val onCurrencySelected: (Currency) -> Unit
) :
    RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder>() {

    class CurrencyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_list_item, parent, false)
        return CurrencyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]
        holder.nameTextView.text = "${currency.name} (${currency.code})"

        holder.itemView.setOnClickListener {
            onCurrencySelected(currency)
        }
    }

    override fun getItemCount(): Int = currencies.size
}