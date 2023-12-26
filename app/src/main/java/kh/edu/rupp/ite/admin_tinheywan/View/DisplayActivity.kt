package kh.edu.rupp.ite.admin_tinheywan.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kh.edu.rupp.ite.admin_tinheywan.databinding.LogoActivityBinding

class DisplayActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: LogoActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed((Runnable {
            startActivity(Intent(this@DisplayActivity, LoginActivity::class.java))
            finish()
        } as Runnable)!!, 3000)
    }
}