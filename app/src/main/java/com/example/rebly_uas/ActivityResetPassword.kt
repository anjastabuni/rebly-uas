package com.example.rebly_uas

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.rebly_uas.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityResetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reset_password)

        binding = ActivityResetPasswordBinding.inflate(layoutInflater)

        binding.tbnReset.setOnClickListener {
                val email : String = binding.edtEmail.text.toString().trim()
                if (email.isEmpty()){
                    binding.edtEmail.error = "Input Email"
                    binding.edtEmail.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.edtEmail.error = "Invalid email"
                    binding.edtEmail.requestFocus()
                    return@setOnClickListener
                }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "cek email for reset password", Toast.LENGTH_SHORT).show()
                    Intent(this, ActivityLogin::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }
                else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

            }

    }
}