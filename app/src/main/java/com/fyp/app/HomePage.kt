package com.fyp.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomePage : AppCompatActivity() {
    private lateinit var SignupBtn: Button
    private lateinit var SigninBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        SignupBtn=findViewById(R.id.SignUpBtn)
        SigninBtn=findViewById(R.id.SigninBtn)
        SignupBtn.setOnClickListener(View.OnClickListener{
            startActivity(Intent(this,SignUp::class.java))
        })
        SigninBtn.setOnClickListener(View.OnClickListener{
            startActivity(Intent(this,SignInActivity::class.java))
        })

    }
}