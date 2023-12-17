package kh.edu.rupp.ite.admin_tinheywan.Model
import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response



class AuthInterceptor constructor(
    private val accessToken:String
) :Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("a","AI = $accessToken")
        var request = chain.request().newBuilder()
        request.addHeader("Authorization","Bearer $accessToken")
        return  chain.proceed(request.build())
    }

}