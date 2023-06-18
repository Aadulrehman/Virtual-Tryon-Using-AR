package com.fyp.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fyp.app.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var mFirebaseAuth : FirebaseAuth
    private lateinit var viewBinder:ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
        mFirebaseAuth=FirebaseAuth.getInstance();

        viewBinder.SignInBtn.setOnClickListener {
            loginAccount()
        }
    }

    private fun loginAccount()
    {
        try {
            if (
                viewBinder.EmailInput.text.isNotBlank()
                && viewBinder.PassInput.text.isNotBlank()
            ) {
                mFirebaseAuth.signInWithEmailAndPassword(
                    viewBinder.EmailInput.text.toString(),
                    viewBinder.PassInput.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "User is logged in",
                                Toast.LENGTH_SHORT
                            ).show();
                            startActivity(Intent(this, GlassesActivity::class.java))
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "${it.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            }
        }
        catch(ex:Exception)
        {
            Toast.makeText(this,"Logical Error : ${ex.message}", Toast.LENGTH_LONG).show()
        }

    }
}