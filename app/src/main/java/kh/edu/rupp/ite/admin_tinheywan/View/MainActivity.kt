package kh.edu.rupp.ite.admin_tinheywan.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kh.edu.rupp.ite.admin_tinheywan.databinding.ActivityMainBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.LogoActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}