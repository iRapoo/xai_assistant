package xyz.quenix.xai_assistant

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class ControlActivity : AppCompatActivity()  {

    private var back_pressed: Long = 0
    private var content: FrameLayout? = null
    private var fragment: Fragment? = null
    private var navigation: BottomNavigationView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                fragment = HomeFragment.Companion.newInstance()
                addFragment(fragment as HomeFragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_create -> {

                fragment = CreateFragment()
                addFragment(fragment as CreateFragment)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {

                fragment = SettingFragment()
                addFragment(fragment as SettingFragment)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        account = intent.extras.getParcelable("account")

        content = findViewById(R.id.content)

        navigation = findViewById(R.id.navigation)

        navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        addFragment(HomeFragment())

    }

    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finish()
            super.onBackPressed()
        }else
            Toast.makeText(baseContext, resources.getString(R.string.double_press), Toast.LENGTH_SHORT).show()

        back_pressed = System.currentTimeMillis()

    }

    companion object {
        var account: GoogleSignInAccount? = null
    }

}
