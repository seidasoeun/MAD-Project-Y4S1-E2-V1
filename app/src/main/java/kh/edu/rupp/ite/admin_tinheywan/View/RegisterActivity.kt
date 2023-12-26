package kh.edu.rupp.ite.admin_tinheywan.View

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitHelper
import kh.edu.rupp.ite.admin_tinheywan.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    // CRUD
    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CRUD ( API call retrofit )
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        // Go Login
        binding.tLogin.setOnClickListener {
            // use to start multipleselect activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //use to destroy old activity
            finish()
        }

        // btn register
        binding.btnRegister.setOnClickListener {
            register()
        }

    }

    // Register
    private fun register(){
        lifecycleScope.launch {
            val email = binding.rEmail
            val username = binding.rUsername
            val password = binding.rPassword
            val cpassword = binding.rCpassword


            val email_value = email.text.toString()
            val username_value = username.text.toString()
            val password_value = password.text.toString()
            val cpassword_value = cpassword.text.toString()

            if (email_value != "" && password_value != "" && cpassword_value != "" && username_value != ""){
                if (password_value == cpassword_value){
                    showLoading("Please wait...")
                    val body = JsonObject().apply {
                        addProperty("email",email_value)
                        addProperty("password",password_value)
                        addProperty("name",username_value)
                    }
                    val result = apiService.createUser(body)
                    if (result.isSuccessful){
                        var status = result.body()?.get("status").toString()
                        val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                        startActivity(intent)
                        finish()

                    }else{
                        Toast.makeText(this@RegisterActivity,"Fail API ...!", Toast.LENGTH_SHORT).show()
                    }

                    progressDialog?.dismiss()
                }else{
                    Toast.makeText(this@RegisterActivity,"Password and Confirm Password are not the same ...!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@RegisterActivity,"Please Fill All FIeld", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(msg:String){
        progressDialog = ProgressDialog.show(this,null,msg,true)
    }
}