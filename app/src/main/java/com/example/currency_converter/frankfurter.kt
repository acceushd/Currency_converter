package com.example.currency_converter

import android.util.Log
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONObject

suspend fun convertCurrency(code: String, to: String = "EUR", amount: Int): String? {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = okhttp3.Request.Builder()
            .url("https://api.frankfurter.app/latest?amount=$amount&from=$code&to=$to")
            .build()
        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string()
            } else {
                Log.e("CurrencyConverter", "API request failed with code: ${response.code}")
                null
            }
        } catch (e: Exception) {
            Log.e("CurrencyConverter", "Currency conversion failed", e)
            null
        }
    }
}

suspend fun converter(code: String, to: String = "EUR", amount: Int): Double? {
    return withContext(Dispatchers.IO) {
        val jsonString = convertCurrency(code, to, amount)
        if (jsonString != null) {
            val jsonObject = JSONObject(jsonString).getJSONObject("rates")
            jsonObject.getDouble(to)
        } else {
            null
        }
    }
}