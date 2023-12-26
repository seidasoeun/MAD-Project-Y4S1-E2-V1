package kh.edu.rupp.ite.admin_tinheywan.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitAuth
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.R
import kh.edu.rupp.ite.admin_tinheywan.View.AdminActivity
import kh.edu.rupp.ite.admin_tinheywan.View.UserActivity
import kotlinx.coroutines.runBlocking

class VipAdapter(
    private var Eywan: data<List<EywanData>> = data<List<EywanData>>()
) : RecyclerView.Adapter<VipAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var vip_title : TextView = itemView.findViewById(R.id.vip_title)
        val vip_price : TextView = itemView.findViewById(R.id.vip_price)
        val vip_type : TextView = itemView.findViewById(R.id.vip_type)
        val vip_image : ImageView = itemView.findViewById(R.id.vip_image)

        val vip_btn : Button = itemView.findViewById(R.id.vip_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vip_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val EywanList = Eywan.data?.get(position)
        holder.vip_title.text = Eywan.data!![position].title
        holder.vip_price.text = Eywan.data!![position].price
        holder.vip_type.text = Eywan.data!![position].type


        if(holder.vip_type.text == "CLOTHES"){
            Glide.with(holder.itemView.context).load(R.drawable.clothes).into(holder.vip_image)
        }else if(holder.vip_type.text == "MATERIAL"){
            Glide.with(holder.itemView.context).load(R.drawable.material).into(holder.vip_image)
        }else if(holder.vip_type.text == "ACCESSORY"){
            Glide.with(holder.itemView.context).load(R.drawable.accessory3).into(holder.vip_image)
        }else if(holder.vip_type.text == "OTHER"){
            Glide.with(holder.itemView.context).load(R.drawable.other).into(holder.vip_image)
        }

        holder.itemView.setOnClickListener {
            // recycler click event
            val intent = Intent(it.context,UserActivity::class.java)
            intent.putExtra("detail_vip","VIP DETAIL")
            intent.putExtra("detail_title",Eywan.data!![position].title)
            intent.putExtra("detail_price",Eywan.data!![position].price)
            intent.putExtra("detail_type",Eywan.data!![position].type)
            intent.putExtra("detail_description",Eywan.data!![position].description)
            intent.putExtra("detail_type",Eywan.data!![position].type)
            // convet int to string for put Extra
            intent.putExtra("detail_id",Eywan.data!![position].id.toString())
            it.context.startActivities(arrayOf(intent))
        }

        // Add to cart
        holder.vip_btn.setOnClickListener {
            val sharedPreferences = it.context.getSharedPreferences("localstorage", Context.MODE_PRIVATE)
            var token = sharedPreferences.getString("TOKEN","").toString()
            var apiService: ApiService = RetrofitAuth.getInstance(token).create(ApiService::class.java)
            val body = JsonObject().apply {
                addProperty("title",Eywan.data!![position].title)
                addProperty("description",Eywan.data!![position].description)
                addProperty("type",Eywan.data!![position].type)
                addProperty("price",Eywan.data!![position].price)
                addProperty("status",Eywan.data!![position].status)
                addProperty("eywan_id",Eywan.data!![position].id)
            }
            runBlocking {
               val task = apiService.getcart(body)
               if (task.isSuccessful){
                   Log.d("oooo","Successful api")
                   showAlertDialog("ALERT","SUCCESSFUL ADD TO CART",holder.itemView.context)
               }else{
                   Log.d("oooo","Error api")
               }
            }

        }
    }


    override fun getItemCount(): Int = Eywan.data?.size!!

    private fun showAlertDialog(title:String,message:String,context:Context){
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK"){
                dialog, which ->
            // do something when click ok
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}