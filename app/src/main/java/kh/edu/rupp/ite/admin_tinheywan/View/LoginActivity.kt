package kh.edu.rupp.ite.admin_tinheywan.View

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kh.edu.rupp.ite.admin_tinheywan.Model.ApiService
import kh.edu.rupp.ite.admin_tinheywan.Model.RetrofitHelper
import kh.edu.rupp.ite.admin_tinheywan.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    // CRUD
    private lateinit var apiService: ApiService
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CRUD ( API call retrofit )
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        // Go Register
        binding.tRegister.setOnClickListener {
            // use to start multipleselect activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            //use to destroy old activity
            finish()
        }

        //btn login
        binding.btnLogin.setOnClickListener {
            login()
        }

    }

    // Login
    private fun login(){
        lifecycleScope.launch {
            val email = binding.etEmail
            val password = binding.etPassword

            val email_value = email.text.toString()
            val password_value = password.text.toString()

            if (email_value != "" && password_value != ""){
                showLoading("Please wait...")
                val body = JsonObject().apply {
                    addProperty("email",email_value)
                    addProperty("password",password_value)
                }
                val result = apiService.loginUser(body)
                if (result.isSuccessful){
                    var status = result.body()?.get("status").toString()
                    if(status == "0"){    // false
                        Toast.makeText(this@LoginActivity,"Incorrect Account", Toast.LENGTH_SHORT).show()
                    }else if(status == "1"){  //user
                        //SharedPreferences
                        val sharePreference = getSharedPreferences("localstorage", Context.MODE_PRIVATE)
                        val editor = sharePreference.edit()
                        editor.putString("TOKEN",result.body()?.get("Token")?.asString!!)
                        Log.d("okhttp",result.body()?.get("Token")?.asString!!)
                        editor.apply()

                        Toast.makeText(this@LoginActivity,"${result.body()?.get("Token").toString()}", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@LoginActivity,UserActivity::class.java)
                        startActivity(intent)
                        finish()

                    }else if(status == "2"){  //admin
                        //SharedPreferences
                        val sharePreference = getSharedPreferences("localstorage", Context.MODE_PRIVATE)
                        val editor = sharePreference.edit()
                        editor.putString("TOKEN",result.body()?.get("Token")?.asString!!)
                        Log.d("okhttp",result.body()?.get("Token")?.asString!!)
                        editor.apply()

                        val intent = Intent(this@LoginActivity,AdminActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }else{
                    Toast.makeText(this@LoginActivity,"FIeld API", Toast.LENGTH_SHORT).show()
                }


                progressDialog?.dismiss()
            }else{
                Toast.makeText(this@LoginActivity,"Please Fill All FIeld", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(msg:String){
        progressDialog = ProgressDialog.show(this,null,msg,true)
    }

}