package kh.edu.rupp.ite.admin_tinheywan.Model.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    // "email" : "value"
    @SerializedName("id") var id:Int? = 0,
    @SerializedName("name") var name:String? = "",
    @SerializedName("email") var email:String? = "",
    @SerializedName("email_verified_at") var email_verified_at:Boolean? = null,
    @SerializedName("created_at") var created_at:String? = "",
    @SerializedName("update_at") var update_at:String? = "",
    @SerializedName("image") var image:String? = "",

)
