package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitAuth
import kh.edu.rupp.ite.admin_tinheywan.View.UserActivity
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserDetaileywanBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DetailEywanFragment : Fragment() {

    private var binding: FragmentUserDetaileywanBinding? = null

    //MVVM
    private val mvvm = MVVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetaileywanBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    };

    override fun onResume() {
        super.onResume()

        val intent = requireActivity().intent.extras
        intent?.remove("detail_vip")

        // go back and clear intent putExtra
        binding!!.detailGoback.setOnClickListener {
            val intent2 = Intent(requireContext(), UserActivity::class.java)
            intent2.removeExtra("detail_vip")
            startActivity(intent2)
        }

        // Image slide click to move
        binding!!.detailLeft.setOnClickListener {
            binding!!.detailImageslider.showPrevious()
        }
        binding!!.detailRight.setOnClickListener {
            binding!!.detailImageslider.showNext()
        }

        binding!!.detailTitle.text = intent?.getString("detail_title")
        binding!!.detailPrice.text = intent?.getString("detail_price")
        binding!!.detailDescription.text = intent?.getString("detail_description")
        binding!!.detailType.text = intent?.getString("detail_type")

        // Eywan Image by ID
        val eywan_id = intent?.getString("detail_id")
        // type return : Int , not Int?
        val eywanid: Int = eywan_id?.toIntOrNull() ?: 0

        val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("TOKEN","").toString()

        // Image slider add by glide x ViewFliper
        GlobalScope.launch { mvvm.getEywanImageById(token,eywanid ) }
        mvvm.eywanimageData.observe(this){
            val flipper = binding!!.detailImageslider
            var imglist = it.data
            if (imglist != null) {
                for(imagelist in imglist ){
                    val imgView = ImageView(requireContext())
                    val width = imgView.width
                    val height = imgView.height
                    Glide.with(this)
                        .load(Uri.parse(imagelist.image))
                        .transform(CenterCrop())
                        .override(width,height)
                        .into(imgView)
                    flipper.addView(imgView)
                }
            }
        }

        // Add to cart
        binding!!.detailBtn.setOnClickListener {
            val sharedPreferences2 = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
            var token2 = sharedPreferences2.getString("TOKEN","").toString()
            var apiService2: ApiService = RetrofitAuth.getInstance(token2).create(ApiService::class.java)

            val body = JsonObject().apply {
                addProperty("title",intent?.getString("detail_title").toString())
                addProperty("description",intent?.getString("detail_description").toString())
                addProperty("type",intent?.getString("detail_type").toString())
                addProperty("price",intent?.getString("detail_price").toString())
                addProperty("status",intent?.getString("detail_status").toString())
                addProperty("eywan_id", intent?.getString("detail_id")!!.toInt())
            }

            runBlocking {
                val task = apiService2.getcart(body)
                if (task.isSuccessful){
                    Log.d("oooo","Successful api")
                    showAlertDialog("ALERT","SUCCESSFUL ADD TO CART",requireContext())
                }else{
                    Log.d("oooo","Error api")
                }
            }

        }

    }

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