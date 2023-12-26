package kh.edu.rupp.ite.admin_tinheywan.Model

import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.data.*
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

    @GET("/api/getallmedium")
    suspend fun getallmedium(): Response<data<List<EywanData>>>

    @GET("/api/getallstandard")
    suspend fun getallstandard(): Response<data<List<EywanData>>>

    @GET("/api/geteywanimagebyid/{id}")
    suspend fun geteywanimagebyid(@Path("id") id: Int): Response<data<List<EywanImageData>>>

    @GET("/api/getuser")
    suspend fun getuser(): Response<data<UserData>>

    // SEARCH
    @GET("/api/getclothes")
    suspend fun getclothes(): Response<data<List<EywanData>>>
    @GET("/api/getaccessory")
    suspend fun getaccessory(): Response<data<List<EywanData>>>
    @GET("/api/getmaterial")
    suspend fun getmaterial(): Response<data<List<EywanData>>>
    @GET("/api/getother")
    suspend fun getother(): Response<data<List<EywanData>>>

    @POST("/api/filter")
    suspend fun getfilter(@Body body: JsonObject): Response<data<List<EywanData>>>

    @POST("/api/cart")
    suspend fun getcart(@Body body: JsonObject): Response<data<EywanCartData>>

    @GET("/api/getcart")
    suspend fun getallcart(): Response<data<List<EywanCartData>>>

    @DELETE("/api/getcartdelete/{id}")
    suspend fun getcartdelete(@Path("id") id:Int): Response<Int>
}