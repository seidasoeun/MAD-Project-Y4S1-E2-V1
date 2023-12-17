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
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitHelper
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.R
import kh.edu.rupp.ite.admin_tinheywan.View.AdminActivity
import kh.edu.rupp.ite.admin_tinheywan.View.fragment.HomeFragment
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserAdapter(
    private var Eywan: data<List<UserData>> = data<List<UserData>>()
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name : TextView = itemView.findViewById(R.id.user_username)
        val email : TextView = itemView.findViewById(R.id.user_email)
        val image : ImageView = itemView.findViewById(R.id.user_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val EywanList = Eywan.data?.get(position)
        val user_id = Eywan.data!![position].id
        holder.name.text = Eywan.data!![position].name
        holder.email.text = Eywan.data!![position].email
        Glide.with(holder.itemView.context).load(Uri.parse(Eywan.data!![position].image)).into(holder.image)

//
        holder.itemView.setOnClickListener {
            // recycler click event
//            val intent = Intent(it.context,DetailActivity::class.java)
//            intent.putExtra("i_title",EywanList.title)
//            it.context.startActivities(arrayOf(intent))

            val sharedPreferences = it.context.getSharedPreferences("localstorage", Context.MODE_PRIVATE)
            var token = sharedPreferences.getString("TOKEN","").toString()
            var apiService: ApiService = RetrofitAuth.getInstance(token).create(ApiService::class.java)

            // run suspen fun api in normal class
            runBlocking {
                val task = apiService.getdeleteuserbyid(user_id!!)
                if(task.isSuccessful){
                    val intent = Intent(it.context,AdminActivity::class.java)
                    it.context.startActivities(arrayOf(intent))
                }else{
                    Log.d("oooo","Error api")
                }
            }
        }

    }


    override fun getItemCount(): Int = Eywan.data?.size!!



}