package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kh.edu.rupp.ite.admin_tinheywan.View.LoginActivity
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentProfileBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    //MVVM
    private val mvvm = MVVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    }

    override fun onResume() {
        super.onResume()

        // Log out
        binding!!.adminBtn.setOnClickListener {
            // clear localstorage
            val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()

            val intent = Intent(getActivity(), LoginActivity::class.java)
            startActivity(intent)

        }

        // Token & admin profile
        val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("TOKEN","").toString()
        GlobalScope.launch { mvvm.getAdminInfo(token) }
        mvvm.adminprofileData.observe(this){
            binding?.adminUsername!!.text = it.name
            binding?.adminEmail!!.text = it.email
            // convet string img to uri (Uri.parse()) && glide or picasso ( prevent bad uri )
            Glide.with(this).load(Uri.parse(it.image)).into(binding?.adminImage!!)
        }


    }
}