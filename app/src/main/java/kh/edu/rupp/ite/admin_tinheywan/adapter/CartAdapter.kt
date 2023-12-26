package kh.edu.rupp.ite.admin_tinheywan.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitAuth
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanCartData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.R
import kh.edu.rupp.ite.admin_tinheywan.View.AdminActivity
import kh.edu.rupp.ite.admin_tinheywan.View.UserActivity
import kh.edu.rupp.ite.admin_tinheywan.View.fragment.PassValue
import kh.edu.rupp.ite.admin_tinheywan.View.fragment.UserOrderFragment
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserOrderBinding
import kotlinx.coroutines.runBlocking

class CartAdapter(
    private var Eywan: data<List<EywanCartData>> = data<List<EywanCartData>>(),
    private var passvalue: PassValue
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var cart_title : TextView = itemView.findViewById(R.id.cart_title)
        val cart_price : TextView = itemView.findViewById(R.id.cart_price)
        val cart_type : TextView = itemView.findViewById(R.id.cart_type)
        val cart_image : ImageView = itemView.findViewById(R.id.cart_image)

        val cart_delete : ImageView = itemView.findViewById(R.id.cart_delete)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_row,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val EywanList = Eywan.data?.get(position)
        val cart_id = Eywan.data!![position].id
        holder.cart_title.text = Eywan.data!![position].title
        holder.cart_price.text = Eywan.data!![position].price
        holder.cart_type.text = Eywan.data!![position].type

        // Get Total
        val price = Eywan.data!![position].price?.toInt()
        val sharedPreferences = holder.itemView.context.getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val price2 = sharedPreferences.getString("total","")?.toIntOrNull() ?: 0
        val total_price = price2 + price!!
        editor.putString("total",total_price.toString())
        editor.apply()
                //Interface
        passvalue.getTotal(sharedPreferences.getString("total","").toString())


        if(holder.cart_type.text == "CLOTHES"){
            Glide.with(holder.itemView.context).load(R.drawable.clothes).into(holder.cart_image)
        }else if(holder.cart_type.text == "MATERIAL"){
            Glide.with(holder.itemView.context).load(R.drawable.material).into(holder.cart_image)
        }else if(holder.cart_type.text == "ACCESSORY"){
            Glide.with(holder.itemView.context).load(R.drawable.accessory3).into(holder.cart_image)
        }else if(holder.cart_type.text == "OTHER"){
            Glide.with(holder.itemView.context).load(R.drawable.other).into(holder.cart_image)
        }

        holder.itemView.setOnClickListener {
            // recycler click event

            val intent = Intent(it.context, UserActivity::class.java)
            intent.putExtra("detail_vip","VIP DETAIL")
            intent.putExtra("detail_title",Eywan.data!![position].title)
            intent.putExtra("detail_price",Eywan.data!![position].price)
            intent.putExtra("detail_type",Eywan.data!![position].type)
            intent.putExtra("detail_description",Eywan.data!![position].description)
            intent.putExtra("detail_type",Eywan.data!![position].type)
            // convet int to string for put Extra
            intent.putExtra("detail_id",Eywan.data!![position].eywan_id.toString())
            it.context.startActivities(arrayOf(intent))
        }

        holder.cart_delete.setOnClickListener {
            val sharedPreferences = it.context.getSharedPreferences("localstorage", Context.MODE_PRIVATE)
            var token = sharedPreferences.getString("TOKEN","").toString()
            var apiService: ApiService = RetrofitAuth.getInstance(token).create(ApiService::class.java)

            // run suspen fun api in normal class
            runBlocking {
                val task = apiService.getcartdelete(cart_id!!)
                if(task.isSuccessful){
                    val intent = Intent(it.context, UserActivity::class.java)
                    intent.putExtra("cart","Avalable cart")
                    it.context.startActivities(arrayOf(intent))
                }else{
                    Log.d("oooo","Error api")
                }
            }
        }

    }


    override fun getItemCount(): Int = Eywan.data?.size!!



}