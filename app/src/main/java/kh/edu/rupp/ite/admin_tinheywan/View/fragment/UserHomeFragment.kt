package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kh.edu.rupp.ite.admin_tinheywan.adapter.UserAdapter
import kh.edu.rupp.ite.admin_tinheywan.adapter.VipAdapter
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentHomeBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserHomeFragment : Fragment() {
    private var binding: FragmentUserHomeBinding? = null

    //MVVM
    private val mvvm = MVVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserHomeBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    };

    override fun onResume() {
        super.onResume()
        val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("TOKEN","").toString()
        GlobalScope.launch { mvvm.getAllVip(token) }
        mvvm.eywanvipData.observe(this){
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(data: data<List<EywanData>>){

        binding!!.rvVip.apply {
            //Grid layout
            layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL,false)
            adapter = VipAdapter(data)
        }

    }
}