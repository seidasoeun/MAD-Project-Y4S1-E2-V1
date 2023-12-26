package kh.edu.rupp.ite.admin_tinheywan.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.R
import kh.edu.rupp.ite.admin_tinheywan.View.UserActivity

class SearchAdapter(
    private var Eywan: data<List<EywanData>> = data<List<EywanData>>()
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var search_title : TextView = itemView.findViewById(R.id.search_title)
        val search_price : TextView = itemView.findViewById(R.id.search_price)
        val search_type : TextView = itemView.findViewById(R.id.search_type)
        val search_image : ImageView = itemView.findViewById(R.id.search_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val EywanList = Eywan.data?.get(position)
        holder.search_title.text = Eywan.data!![position].title
        holder.search_price.text = Eywan.data!![position].price
        holder.search_type.text = Eywan.data!![position].type


        if(holder.search_type.text == "CLOTHES"){
            Glide.with(holder.itemView.context).load(R.drawable.clothes).into(holder.search_image)
        }else if(holder.search_type.text == "MATERIAL"){
            Glide.with(holder.itemView.context).load(R.drawable.material).into(holder.search_image)
        }else if(holder.search_type.text == "ACCESSORY"){
            Glide.with(holder.itemView.context).load(R.drawable.accessory3).into(holder.search_image)
        }else if(holder.search_type.text == "OTHER"){
            Glide.with(holder.itemView.context).load(R.drawable.other).into(holder.search_image)
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
            intent.putExtra("detail_id",Eywan.data!![position].id.toString())
            it.context.startActivities(arrayOf(intent))
        }

    }


    override fun getItemCount(): Int = Eywan.data?.size!!


}