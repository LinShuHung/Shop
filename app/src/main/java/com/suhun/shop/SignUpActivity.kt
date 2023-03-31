package com.suhun.shop

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.suhun.shop.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    var tag:String = SignUpActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signButton.setOnClickListener {
            val email:String = binding.emailInput.text.toString()
            val password:String = binding.passwordInput.text.toString()
            Log.d(tag, "user mail is $email, user password is $password")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    AlertDialog.Builder(this)
                        .setTitle("Sign up")
                        .setMessage("Create account success").setPositiveButton("Ok"){dialog, which->
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        .show()
                }else{
                    AlertDialog.Builder(this)
                        .setTitle("Sign up")
                        .setMessage(it.exception?.message)
                        .setPositiveButton("Ok", null)
                        .show()
                }
            }
        }
    }
}