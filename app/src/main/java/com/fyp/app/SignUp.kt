package com.fyp.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fyp.app.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUp : AppCompatActivity() {

    private lateinit var mFirebaseAuth : FirebaseAuth
    private lateinit var viewBinder: ActivitySignUpBinding
    private lateinit var mFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)

        mFirebaseAuth=FirebaseAuth.getInstance()
        mFirestore= FirebaseFirestore.getInstance()
        viewBinder.SignUpBtn.setOnClickListener {
            addDocWithClassModel()
            createAccount()
        }
    }
    private fun addDocWithClassModel() {
        try
        {
            if(viewBinder.NameInput.text.isNotBlank()
                && viewBinder.DobInput.text.isNotBlank()
            )
            {
                val currentEmp=UserEnter(viewBinder.NameInput.text.toString(),
                    viewBinder.DobInput.text.toString()
                )

                mFirestore.collection("User")
                    .document()
                    .set(currentEmp)
                    .addOnSuccessListener {_:Void? ->
                        //Toast.makeText(applicationContext,"Data Added", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { fireStoreEx->
                        Toast.makeText(applicationContext,"Ex:${fireStoreEx.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            else
            {
                Toast.makeText(applicationContext,"Some Fields Are Empty", Toast.LENGTH_SHORT).show()
            }
        }
        catch (ex:Exception)
        {
            Toast.makeText(applicationContext,ex.message, Toast.LENGTH_SHORT).show()
        }

    }
    private fun createAccount()
    {
        try
        {
            if(viewBinder.EmailInput.text.isNotBlank()
                && viewBinder.PassInput.text.isNotBlank())
            {
                mFirebaseAuth.createUserWithEmailAndPassword(
                    viewBinder.EmailInput.text.toString(),
                    viewBinder.PassInput.text.toString()
                )
                    .addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            Toast.makeText(applicationContext, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                        else
                        {
                            Toast.makeText(applicationContext, "${it.exception?.message}", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
            else
            {
                Toast.makeText(applicationContext, "Some Fields Are Empty", Toast.LENGTH_SHORT).show();
            }
        }
        catch (ex: Exception)
        {
            Toast.makeText(applicationContext, "${ex.message}", Toast.LENGTH_SHORT).show()
        }

    }
}