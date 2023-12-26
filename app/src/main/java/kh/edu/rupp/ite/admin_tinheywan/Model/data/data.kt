package kh.edu.rupp.ite.admin_tinheywan.Model.data

import com.google.gson.annotations.SerializedName

data class data<T>(
    @SerializedName("data") var data: T? = null
)
