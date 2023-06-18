package com.example.preject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.preject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener {
            val email = binding.signUpEmail.text.toString()
            val password = binding.SignUpPassword.text.toString()
            val confPass = binding.signUpConfPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confPass.isNotEmpty()) {
                if (password == confPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else {
                    Toast.makeText(this, "Passwords are not identical", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "The fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}