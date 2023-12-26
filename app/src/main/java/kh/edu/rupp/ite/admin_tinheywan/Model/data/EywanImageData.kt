package kh.edu.rupp.ite.admin_tinheywan.Model.data

import com.google.gson.annotations.SerializedName

data class EywanImageData(
    @SerializedName("id") var id:Int? = 0,
    @SerializedName("eywan_id") var eywan_id:Int? = 0,
    @SerializedName("image") var image:String? = "",
)
