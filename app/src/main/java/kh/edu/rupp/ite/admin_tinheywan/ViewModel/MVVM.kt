package kh.edu.rupp.ite.admin_tinheywan.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitAuth
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitHelper
import kh.edu.rupp.ite.admin_tinheywan.Model.data.*

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

    private val _eywanmediumData = MutableLiveData<data<List<EywanData>>>()
    val eywanmediumData : LiveData<data<List<EywanData>>>
        get() = _eywanmediumData

    private val _eywanstandardData = MutableLiveData<data<List<EywanData>>>()
    val eywanstandardData : LiveData<data<List<EywanData>>>
        get() = _eywanstandardData

    private val _eywanimageData = MutableLiveData<data<List<EywanImageData>>>()
    val eywanimageData : LiveData<data<List<EywanImageData>>>
        get() = _eywanimageData

    // User Info
    private val _UserProfileData = MutableLiveData<data<UserData>>()
    val UserProfileData : LiveData<data<UserData>>
        get() = _UserProfileData

    // SEARCH
        // Clothes
    private val _ClothesData = MutableLiveData<data<List<EywanData>>>()
    val ClothesData : LiveData<data<List<EywanData>>>
        get() = _ClothesData
        // Accessory
    private val _AccessoryData = MutableLiveData<data<List<EywanData>>>()
    val AccessoryData : LiveData<data<List<EywanData>>>
        get() = _AccessoryData
        // Material
    private val _MaterialData = MutableLiveData<data<List<EywanData>>>()
    val MaterialData : LiveData<data<List<EywanData>>>
        get() = _MaterialData
        // Other
    private val _OtherData = MutableLiveData<data<List<EywanData>>>()
    val OtherData : LiveData<data<List<EywanData>>>
        get() = _OtherData
    // Filter
    private val _FilterData = MutableLiveData<data<List<EywanData>>>()
    val FilterData : LiveData<data<List<EywanData>>>
        get() = _FilterData
    // Cart
    private val _CartData = MutableLiveData<data<List<EywanCartData>>>()
    val CartData : LiveData<data<List<EywanCartData>>>
        get() = _CartData

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

    suspend fun getAllMedium(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getallmedium()
        if (task.isSuccessful){
            _eywanmediumData.postValue(task.body())
        }else{
            Log.d("oooo","Error")
        }
    }

    suspend fun getAllStandard(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getallstandard()
        if (task.isSuccessful){
            _eywanstandardData.postValue(task.body())
        }else{
            Log.d("oooo","Error")
        }
    }

    suspend fun getEywanImageById(Token:String, eywan_id: Int){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.geteywanimagebyid(eywan_id)
        if (task.isSuccessful){
            _eywanimageData.postValue(task.body())
        }else{
            Log.d("oooo","Error")
        }
    }

    suspend fun getUserInfo(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getuser()
        if(task.isSuccessful){
            _UserProfileData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getClothes(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getclothes()
        if(task.isSuccessful){
            _ClothesData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getAccessory(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getaccessory()
        if(task.isSuccessful){
            _AccessoryData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getMaterial(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getmaterial()
        if(task.isSuccessful){
            _MaterialData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getOther(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getother()
        if(task.isSuccessful){
            _OtherData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getFilter(Token:String,text:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val body = JsonObject().apply {
            addProperty("text",text)
        }
        val task = apiService.getfilter(body)
        if(task.isSuccessful){
            _FilterData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }

    suspend fun getAllCart(Token:String){
        apiService = RetrofitAuth.getInstance(Token).create(ApiService::class.java)
        val task = apiService.getallcart()
        if(task.isSuccessful){
            _CartData.postValue(task.body())
        }else{
            Log.d("oooo","Error api")
        }
    }


}