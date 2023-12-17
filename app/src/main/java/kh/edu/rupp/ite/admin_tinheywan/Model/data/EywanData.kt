package kh.edu.rupp.ite.admin_tinheywan.Model.data

import com.google.gson.annotations.SerializedName

data class EywanData(
    @SerializedName("id") var id:Int? = 0,
    @SerializedName("title") var title:String? = "",
    @SerializedName("description") var description:String? = "",
    @SerializedName("price") var price:String? = "",
    @SerializedName("type") var type:String? = "",
    @SerializedName("user_id") var user_id:Int? = 0,
    @SerializedName("created_at") var created_at:String? = "",
    @SerializedName("update_at") var update_at:String? = "",
    @SerializedName("status") var status:String? = "",
)
