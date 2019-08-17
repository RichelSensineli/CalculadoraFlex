package io.richel.calculadoraflex.iu.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import io.richel.calculadoraflex.R
import io.richel.calculadoraflex.iu.form.FormActivity
import io.richel.calculadoraflex.iu.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDO_SPLASHSCREEN = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        carregar()

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isFirstOpen = preferences.getBoolean("open_first", true)

        if(isFirstOpen){
            markAppAlreadyOpen(preferences)
            showSplash()
        }else{
            showLogin()
        }
    }

    private fun carregar(){
        //carrega animação
        var anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()

        ivLogo.clearAnimation()

        //roda animation
        ivLogo.startAnimation(anim)

        //chama próxima tela após 3,5 segundos definido na SPLASH_DISPLAY_LENGTH
        Handler().postDelayed({
            val proximaTela = Intent(this@SplashActivity, FormActivity::class.java)
            startActivity(proximaTela)
            finish()
        }, TEMPO_AGUARDO_SPLASHSCREEN)
    }

    private fun markAppAlreadyOpen(prefereces: SharedPreferences){
        val editor = prefereces.edit()
        editor.putBoolean("open_first", false)
        editor.apply()
    }

    private fun showSplash(){
        val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextScreen)
        finish()
    }

    private fun showLogin(){

    }
}
