package kh.edu.rupp.ite.admin_tinheywan.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitAuth
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.R
import kh.edu.rupp.ite.admin_tinheywan.View.AdminActivity
import kotlinx.coroutines.runBlocking

class VipAdapter(
    private var Eywan: data<List<EywanData>> = data<List<EywanData>>()
) : RecyclerView.Adapter<VipAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var vip_title : TextView = itemView.findViewById(R.id.vip_title)
        val vip_price : TextView = itemView.findViewById(R.id.vip_price)
        val vip_type : TextView = itemView.findViewById(R.id.vip_type)
        val vip_image : ImageView = itemView.findViewById(R.id.vip_image)
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
//        Glide.with(holder.itemView.context).load(Uri.parse(Eywan.data!![position].image)).into(holder.image)

    }


    override fun getItemCount(): Int = Eywan.data?.size!!



}