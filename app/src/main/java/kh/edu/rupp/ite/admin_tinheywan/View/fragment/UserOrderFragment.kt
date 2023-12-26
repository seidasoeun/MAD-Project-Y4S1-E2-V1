package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanCartData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kh.edu.rupp.ite.admin_tinheywan.adapter.CartAdapter
import kh.edu.rupp.ite.admin_tinheywan.adapter.VipAdapter
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserHomeBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserOrderBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserOrderFragment : Fragment(),PassValue {

    private var binding: FragmentUserOrderBinding? = null

    //MVVM
    private val mvvm = MVVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserOrderBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    };

    override fun onResume() {
        super.onResume()
        // Token & remove storage total
        val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("TOKEN","").toString()
        sharedPreferences.edit().remove("total").apply()

        // remove cart intent
        val intent = requireActivity().intent.extras
        intent?.remove("cart")

        //Get Cart // global scope asyn suspen fun
        GlobalScope.launch { mvvm.getAllCart(token) }
        mvvm.CartData.observe(this){
            setupRecyclerView(it)
        }


    }

    private fun setupRecyclerView(data: data<List<EywanCartData>>){

        binding!!.rvCart.apply {
            //Grid layout
            layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL,false)
            adapter = CartAdapter(data,this@UserOrderFragment)
        }

    }

    override fun getTotal(total_value: String) {
        binding!!.cartTotal.text = total_value
    }


}