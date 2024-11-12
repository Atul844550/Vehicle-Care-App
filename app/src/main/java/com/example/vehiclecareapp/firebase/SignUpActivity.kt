package com.example.vehiclecareapp.firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vehiclecareapp.R
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var sign: Button
    private lateinit var login: TextView

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()
        sign = findViewById(R.id.sign)
        login = findViewById(R.id.login)

        // Set up the signup button click listener
        sign.setOnClickListener {
            val name = findViewById<EditText>(R.id.name).text.toString().trim()
            val email = findViewById<EditText>(R.id.email).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()

            // Validate user input
            if (name.isEmpty()) {
                findViewById<EditText>(R.id.name).error = "Name is required"
                findViewById<EditText>(R.id.name).requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                findViewById<EditText>(R.id.email).error = "Valid email is required"
                findViewById<EditText>(R.id.email).requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                findViewById<EditText>(R.id.password).error =
                    "Password must be at least 6 characters"
                findViewById<EditText>(R.id.password).requestFocus()
                return@setOnClickListener
            }

            // Call the Firebase sign-up method
            signUpUser(email, password)
        }

        // Handle login click
        login.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    // Sign up new user using Firebase Authentication
    private fun signUpUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Successfully signed up
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                    // Navigate to another activity after successful signup (e.g., Dashboard or Login)
                    startActivity(Intent(this, SignInActivity::class.java))
                    finish()
                } else {
                    // Sign up failed
                    Toast.makeText(
                        this,
                        "Sign-up failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}



