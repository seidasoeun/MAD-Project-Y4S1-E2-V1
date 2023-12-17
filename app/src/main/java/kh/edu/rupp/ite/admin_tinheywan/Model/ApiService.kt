package kh.edu.rupp.ite.admin_tinheywan.Model

import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserResponse
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Admin
    @POST("/api/register")
    suspend fun createUser(@Body body: JsonObject): Response<JsonObject>

    @POST("/api/login")
    suspend fun loginUser(@Body body: JsonObject): Response<JsonObject>

    @GET("/api/getalluser")
    suspend fun getalluser(): Response<data<List<UserData>>>

        //Display Profile login
    @GET("/api/displayprofile")
    suspend fun profile(): Response<UserResponse>

        //Eywan
    @GET("/api/eywan")
    suspend fun getUserByID(): Response<List<UserResponse>>
    @POST("/api/eywan")
    suspend fun getposteywan(@Body body: JsonObject): Response<data<EywanData>>


        // count total
    @GET("/api/countuser")
    suspend fun getcountuser(): Response<Int>

    @GET("/api/counteywan")
    suspend fun getcounteywan(): Response<Int>

    @GET("/api/counteywansold")
    suspend fun getcounteywansold(): Response<Int>

    @GET("/api/adminprofile")
    suspend fun getadminprofile(): Response<UserResponse>

        // delete user use in adapter
    @DELETE("/api/deleteuserbyid/{id}")
    suspend fun getdeleteuserbyid(@Path("id") id:Int): Response<Int>

        // Post Eywant image to DB
    @POST("/api/eywan_image")
    suspend fun postEywanImage(@Body body: JsonObject): Response<JsonObject>

// USER
    @GET("/api/getallvip")
    suspend fun getallvip(): Response<data<List<EywanData>>>
}