package com.capstone.skinory.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.capstone.skinory.MainActivity
import com.capstone.skinory.R
import com.capstone.skinory.databinding.ActivitySplashBinding
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var tokenDataStore: TokenDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        tokenDataStore = TokenDataStore.getInstance(this)

        Handler().postDelayed({
            checkToken()
            finish()
        }, 2000)
    }

    private fun checkToken() {
        lifecycleScope.launch {
            tokenDataStore.token.collect { token ->
                if (!token.isNullOrEmpty()) {
                    // Arahkan ke MainActivity jika token ada
                    startActivity(Intent(this@Splash, MainActivity::class.java))
                    finish()
                }
                else{
                    startActivity(Intent(this@Splash, LoginActivity::class.java))
                }
            }
        }
    }
}