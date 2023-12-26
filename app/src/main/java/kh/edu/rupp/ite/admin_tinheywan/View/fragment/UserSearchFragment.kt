package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.admin_tinheywan.Model.data.EywanData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.ViewModel.MVVM
import kh.edu.rupp.ite.admin_tinheywan.adapter.MediumAdapter
import kh.edu.rupp.ite.admin_tinheywan.adapter.SearchAdapter
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserHomeBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentUserSearchBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserSearchFragment : Fragment(),TextWatcher{

    private var binding: FragmentUserSearchBinding? = null

    //MVVM
    private val mvvm = MVVM()

    private lateinit var adapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserSearchBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    };

    override fun onResume() {
        super.onResume()
        // TOKEN
        val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("TOKEN","").toString()
        // clear total cart
        sharedPreferences.edit().remove("total").apply()

        // Clothes
        binding!!.searchClothes.setOnClickListener {
            // CLOTHES
            GlobalScope.launch { mvvm.getClothes(token) }
            mvvm.ClothesData.observe(this){
                setupRecyclerView(it)
            }
        }

        // Accessory
        binding!!.searchAccessory.setOnClickListener {
            // CLOTHES
            GlobalScope.launch { mvvm.getAccessory(token) }
            mvvm.AccessoryData.observe(this){
                setupRecyclerView(it)
            }
        }

        // Material
        binding!!.searchMaterial.setOnClickListener {
            // CLOTHES
            GlobalScope.launch { mvvm.getMaterial(token) }
            mvvm.MaterialData.observe(this){
                setupRecyclerView(it)
            }
        }

        // Other
        binding!!.searchOther.setOnClickListener {
            // CLOTHES
            GlobalScope.launch { mvvm.getOther(token) }
            mvvm.OtherData.observe(this){
                setupRecyclerView(it)
            }
        }

        binding!!.searchSearch.addTextChangedListener(this)
    }


    private fun setupRecyclerView(data: data<List<EywanData>>){

        binding!!.rvSearch.apply {
            //Grid layout
            layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL,false)
            adapter = SearchAdapter(data)
        }

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("TOKEN","").toString()
        GlobalScope.launch { mvvm.getFilter(token,p0.toString()) }
        mvvm.FilterData.observe(this){
            setupRecyclerView(it)
        }
    }

    override fun afterTextChanged(p0: Editable?) {}

}