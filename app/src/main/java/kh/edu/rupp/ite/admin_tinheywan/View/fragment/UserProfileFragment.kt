package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kh.edu.rupp.ite.admin_tinheywan.View.LoginActivity
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentProfileBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserHomeBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserProfileBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserProfileFragment : Fragment() {

    private var binding: FragmentUserProfileBinding? = null

    //MVVM
    private val mvvm = MVVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    };

    override fun onResume() {
        super.onResume()

        // clear total cart
        val sharedPreferences2 = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        sharedPreferences2.edit().remove("total").apply()

        // Log out
        binding!!.userBtn.setOnClickListener {
            // clear localstorage
            val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()

            val intent = Intent(getActivity(), LoginActivity::class.java)
            startActivity(intent)
        }


        val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("TOKEN","").toString()

        // Get User Info
        GlobalScope.launch { mvvm.getUserInfo(token) }
        mvvm.UserProfileData.observe(this){
            binding!!.userUsername.text = it.data?.name
            binding!!.userEmail.text = it.data?.email
            Glide.with(this).load(Uri.parse(it.data?.image)).into(binding?.userImage!!)
        }
    }

}