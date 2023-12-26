package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kh.edu.rupp.ite.admin_tinheywan.adapter.UserAdapter
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null

    //MVVM
    private val mvvm = MVVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    };

    override fun onResume() {
        // get all user
        super.onResume()
        GlobalScope.launch { mvvm.getAllUser() }
        mvvm.userData.observe(this){
            setupRecyclerView(it)
        }

        // total user
        GlobalScope.launch { mvvm.getCountUser() }
        mvvm.countuserData.observe(this){
            binding?.totaluser!!.text = it.toString()
        }

        // total product
        GlobalScope.launch { mvvm.getCountEywan() }
        mvvm.counteywanData.observe(this){
            binding?.totalproduct!!.text = it.toString()
        }

        // total product sold
        GlobalScope.launch { mvvm.getCountEywanSold() }
        mvvm.counteywansoldData.observe(this){
            binding?.totalproductsold!!.text = it.toString()
        }


    }

    private fun setupRecyclerView(data:data<List<UserData>>){

        binding!!.rvAdmin.apply {
            //Grid layout
            layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL,false)
            adapter = UserAdapter(data)
        }

    }



}