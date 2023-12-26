package kh.edu.rupp.ite.admin_tinheywan.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.admin_tinheywan.R
import kh.edu.rupp.ite.admin_tinheywan.View.fragment.*
import kh.edu.rupp.ite.admin_tinheywan.databinding.ActivityAdminBinding
import kh.edu.rupp.ite.admin_tinheywan.databinding.ActivityUserBinding
import java.nio.file.attribute.UserPrincipalLookupService

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // First show Home
//        showFragment(UserHomeFragment())

        // set Event
        binding.UserNav.setOnItemSelectedListener { item ->
            if (item.itemId === R.id.user_home) {
                showFragment(UserHomeFragment())
            }else if(item.itemId === R.id.user_search){
                showFragment(UserSearchFragment())
            }else if(item.itemId === R.id.user_order){
                showFragment(UserOrderFragment())
            }else if(item.itemId === R.id.user_profile){
                showFragment(UserProfileFragment())
            }
            true
        }

        // Detail
        val intent = intent
        val detail_vip = intent?.getStringExtra("detail_vip")
        val cart = intent?.getStringExtra("cart")
        if(detail_vip != null){
            showFragment(DetailEywanFragment())
        }else if(cart != null){
            showFragment(UserOrderFragment())
        }else{
            // First show Home
            showFragment(UserHomeFragment())
        }

    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.UserFrame, fragment)
        fragmentTransaction.commit()
    }

}