package kh.edu.rupp.ite.admin_tinheywan.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.admin_tinheywan.R
import kh.edu.rupp.ite.admin_tinheywan.View.fragment.HomeFragment
import kh.edu.rupp.ite.admin_tinheywan.View.fragment.ProductFragment
import kh.edu.rupp.ite.admin_tinheywan.View.fragment.ProfileFragment
import kh.edu.rupp.ite.admin_tinheywan.databinding.ActivityAdminBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.ActivityMainBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // First show Home
        showFragment(HomeFragment())

        // set Event
        binding.BtnNav.setOnItemSelectedListener { item ->
            if (item.itemId === R.id.home) {
                showFragment(HomeFragment())
            }
            else if (item.itemId === R.id.profile) {
                showFragment(ProfileFragment())
            }
            else if(item.itemId === R.id.product){
                showFragment(ProductFragment())
            }
            true
        }


    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.MainFrame, fragment)
        fragmentTransaction.commit()
    }
}