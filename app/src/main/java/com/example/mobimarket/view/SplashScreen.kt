package com.example.mobimarket.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.mobimarket.R
import com.example.mobimarket.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAGS_CHANGED
        )

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
//        binding.mainCart.alpha=0f
//        binding.mainCart.animate().setDuration(1500).alpha(1f).withEndAction{
//            val i= Intent(this, LoginFragment::class.java)
//            startActivity(i)
//            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
//        }


    }
}