package kh.edu.rupp.ite.admin_tinheywan.View.fragment

import android.R
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitAuth
import kh.edu.rupp.ite.admin_tinheywan.Model.data.UserData
import kh.edu.rupp.ite.admin_tinheywan.Model.data.data
import kh.edu.rupp.ite.admin_tinheywan.View.AdminActivity
import kh.edu.rupp.ite.admin_tinheywan.View.LoginActivity
import kh.edu.rupp.ite.admin_tinheywan.adapter.AdminMultipleImageAdapter
import kh.edu.rupp.ite.admin_tinheywan.adapter.UserAdapter
import kh.edu.rupp.ite.admin_tinheywan.databinding.FragmentProductBinding
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    // Dropdown Menu
        //type
    val lists = arrayOf("CLOTHES","MATERIAL","ACCESSORY","OTHER")
    var ddm:String? = null
        //status
    val lists2 = arrayOf("VIP","MEDIUM","STANDARD")
    var ddm2:String? = null

    // Upload Image
    private var imageUriList = mutableListOf<Uri?>()
    private var fileNameList = mutableListOf<String?>()
    private var currentFile: Uri? = null

    var url:String? = null
    var lis = mutableListOf<String>()

    private var imageReference = Firebase.storage.reference

    // CRUD
    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? = null

    private var binding: FragmentProductBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        //        binding.profileID.setOnClickListener(view -> startProfileActivity());
        return binding!!.getRoot()
    }

    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()

        // TYPE Dropdown Menu
        val spinner = binding!!.sDropdownMenu
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item,lists)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(requireContext(),"Select lists ="+lists[p2], Toast.LENGTH_SHORT).show()
                ddm = lists[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // STATUS Dropdown Menu
        val spinner2 = binding!!.sDropdownMenu2
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item,lists2)
        spinner2.adapter = arrayAdapter2
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(requireContext(),"Select lists ="+lists2[p2], Toast.LENGTH_SHORT).show()
                ddm2 = lists2[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // Upload Image
        binding!!.btnUploadImage.setOnClickListener{
            Intent(Intent.ACTION_GET_CONTENT).also {
                //use to clear the list
                imageUriList = mutableListOf()
                fileNameList = mutableListOf()

                Intent(Intent.ACTION_GET_CONTENT).also {
                    //filter image only
                    it.type = "image/*"
                    //use to allow multiple selection of image
                    it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
                    imageLauncher.launch(Intent.createChooser(it,"select photo"))
                }
            }
        }

        // POST
        binding!!.adminBtnpost.setOnClickListener {
            post()
        }



    }


    // upload image function
    private val imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == AppCompatActivity.RESULT_OK){
            result?.data?.clipData?.let {
                val clipDataCount = it.itemCount
                // previous function before on getting filename
                for (clipPosition in 0 until clipDataCount){
                    addItems(it.getItemAt(clipPosition).uri,getFilenameFromUri(requireContext(),it.getItemAt(clipPosition).uri))
                }

                //setup recycleview
                setupRecyclerView()

                // upload img to firebase
                for (position in 0 until imageUriList.size){
                    currentFile = imageUriList[position]
                    uploadImageToStorage(fileNameList[position])
                }
            } ?: kotlin.run {  // for user select 1 photo , because .let doesn't contain 2 or more photo
                result.data?.data?.let {
                    addItems(it,getFilenameFromUri(requireContext(),it))
                    setupRecyclerView()

                    // upload img to firebase
                    for (position in 0 until imageUriList.size){
                        currentFile = imageUriList[position]
                        uploadImageToStorage(fileNameList[position])
                    }
                }
            }
        }else{
            Toast.makeText(requireContext(),"Canceled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImageToStorage(filename: String?){
        try {
            showLoading("Please wait...")
            currentFile?.let {
                imageReference.child("images/$filename").putFile(it).addOnSuccessListener {
                    Toast.makeText(requireContext(),"Upload Success !",Toast.LENGTH_SHORT).show()
                    val fileRef = imageReference.child("images/$filename")
                    fileRef.downloadUrl.addOnSuccessListener { uri ->
                        url = uri.toString()
                        lis.add(url!!)
                    }
                } .addOnFailureListener{
                    Toast.makeText(requireContext(),"Error Upload",Toast.LENGTH_SHORT).show()
                    progressDialog?.dismiss()
                }
            }
            progressDialog?.dismiss()
        }catch (e: Exception){
            Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFilenameFromUri(context: Context, uri:Uri): String?{
        val filename:String?
        val cursor = context.contentResolver.query(uri,null,null,null,null)
        cursor?.moveToFirst()
        filename = cursor?.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
        cursor?.close()
        return filename
    }

    private fun addItems(imageUriLists: Uri?, filenameLists: String?){
        imageUriList.add(imageUriLists)
        fileNameList.add(filenameLists)
    }

    private fun setupRecyclerView(){

        binding!!.rvImage.apply {
            //Grid layout
            layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL,false)
            adapter = AdminMultipleImageAdapter(imageUriList,fileNameList)
        }

    }

    private fun showLoading(msg:String){
        progressDialog = ProgressDialog.show(requireContext(),null,msg,true)
    }


    // CRUD
    private fun post(){
        lifecycleScope.launch {
            val title = binding!!.adminEtTitle
            val description = binding!!.adminEtDescription
            val price = binding!!.adminEtPrice

            val title_value = title.getText().toString()
            val description_value = description.getText().toString()
            val dropdownMenu_value = ddm
            val status = ddm2
            val price_value = price.getText().toString()

            if (title_value != "" && description_value  != "" && dropdownMenu_value != "" && status != "" && price_value != "" && lis != null){
                showLoading("Please wait...")
                val body = JsonObject().apply {
                    addProperty("title",title_value)
                    addProperty("description",description_value)
                    addProperty("price",price_value)
                    addProperty("type",dropdownMenu_value)
                    addProperty("status",status)
                }
                val sharedPreferences = requireActivity().getSharedPreferences("localstorage", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("TOKEN","").toString()
                apiService = RetrofitAuth.getInstance(token).create(ApiService::class.java)
                val result = apiService.getposteywan(body)
                if(result.isSuccessful){
                    Toast.makeText(requireContext(),"Upload Success",Toast.LENGTH_SHORT).show()
                    Log.d("aa","${result.body()!!.data?.id}")

                    // post image in db
                    val product_id = result.body()!!.data?.id
                    val eywan_id = product_id?.toInt()   // convert to Int
                    for(item in lis){
                        val body = JsonObject().apply {
                            addProperty("eywan_id",eywan_id)
                            addProperty("image",item)
                        }
                        var resilt_image = apiService.postEywanImage(body)
                    }

                }else{
                    Toast.makeText(requireContext(),"Upload Fail",Toast.LENGTH_SHORT).show()
                }
                progressDialog?.dismiss()
                val intent = Intent(requireContext(), AdminActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(),"Please Fill All FIeld",Toast.LENGTH_SHORT).show()
            }
        }
    }

}