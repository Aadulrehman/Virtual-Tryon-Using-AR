package com.fyp.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        startActivity(Intent(this, SignUp::class.java))
=======
        startActivity(Intent(this, SplashScreen::class.java))
>>>>>>> Stashed changes


    }



}
