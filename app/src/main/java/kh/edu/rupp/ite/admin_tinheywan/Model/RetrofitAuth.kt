package kh.edu.rupp.ite.admin_tinheywan.Model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAuth{
    private const val baseUrl = "http://10.0.2.2:8000/"

    fun getInstance(Token:String): Retrofit {
        Log.d("a","a= $Token")
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOKHttoClient(Token))
            .build()
    }


    fun providesOKHttoClient(Token:String): OkHttpClient {
        Log.d("aa","aa= $Token")
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(
            AuthInterceptor(
                Token
            )
        ).addInterceptor(interceptor).build()
    }

    //error para


}