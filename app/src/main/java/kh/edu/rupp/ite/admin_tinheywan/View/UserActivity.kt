package kh.edu.rupp.ite.admin_tinheywan.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // First show Home
        showFragment(UserHomeFragment())

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
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.MainFrame, fragment)
        fragmentTransaction.commit()
    }

}