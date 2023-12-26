package kh.edu.rupp.ite.admin_tinheywan.Model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    private const val baseUrl = "http://10.0.2.2:8000/"

    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}