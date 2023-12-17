package kh.edu.rupp.ite.admin_tinheywan.ViewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitAuth
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitHelper
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserResponse
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data

class MVVM :ViewModel(){

    // CRUD
    private lateinit var apiService: ApiService

// ADMIN
    // Live  data
    private val _eywanData = MutableLiveData<List<UserResponse>>()
    val eywanData : LiveData<List<UserResponse>>
        get() = _eywanData
    // data all user
    private val _userData = MutableLiveData<data<List<UserData>>>()
    val userData : LiveData<data<List<UserData>>>
        get() = _userData

    // data count user
    private val _countuserData = MutableLiveData<Int>()
    val countuserData : LiveData<Int>
        get() = _countuserData

    // data count product
    private val _counteywanData = MutableLiveData<Int>()
    val counteywanData : LiveData<Int>
        get() = _counteywanData

    // data count product sold
    private val _counteywansoldData = MutableLiveData<Int>()
    val counteywansoldData : LiveData<Int>
        get() = _counteywansoldData

    // data admin profile
    private val _adminprofileData = MutableLiveData<UserResponse>()
    val adminprofileData : LiveData<UserResponse>
        get() = _adminprofileData

//USER
    private val _eywanvipData = MutableLiveData<data<List<EywanData>>>()
    val eywanvipData : LiveData<data<List<EywanData>>>
        get() = _eywanvipData


      suspend fun loadEywan(){
          apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val task = apiService.getUserByID()
        if (task.isSuccessful){
            _eywanData.postValue(task.body())
        }else{
            Log.d("oooo","Error")
        }
      }

    suspend fun getAllUser(){
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val task = apiService.getalluser()
        if(task.isSuccessful){
            _userData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getCountUser(){
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val task = apiService.getcountuser()
        if(task.isSuccessful){
            _countuserData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getCountEywan(){
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val task = apiService.getcounteywan()
        if(task.isSuccessful){
            _counteywanData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getCountEywanSold(){
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val task = apiService.getcounteywansold()
        if(task.isSuccessful){
            _counteywansoldData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    // Profile Admin
    suspend fun getAdminInfo(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getadminprofile()
        if(task.isSuccessful){
            _adminprofileData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }



// USER
    suspend fun getAllVip(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getallvip()
        if (task.isSuccessful){
            _eywanvipData.postValue(task.body())
        }else{
            Log.d("oooo","Error")
        }
    }


}