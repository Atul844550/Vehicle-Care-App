package com.example.vehiclecareapp.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vehiclecareapp.MainActivity
import com.example.vehiclecareapp.R
import com.google.firebase.auth.FirebaseAuth

class  SignInActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var loginButton: Button
    private lateinit var newregister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()
        loginButton = findViewById(R.id.loginButton)
        newregister = findViewById(R.id.newregister)

        // Set up the login button click listener
        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.username).text.toString().trim()
            val password = findViewById<EditText>(R.id.password).text.toString().trim()

            // Validate email and password input
            if (email.isEmpty()) {
                findViewById<EditText>(R.id.username).error = "Email is required"
                findViewById<EditText>(R.id.username).requestFocus()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                findViewById<EditText>(R.id.username).error = "Please enter a valid email"
                findViewById<EditText>(R.id.username).requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                findViewById<EditText>(R.id.password).error = "Password is required"
                findViewById<EditText>(R.id.password).requestFocus()
                return@setOnClickListener
            }

            // Call the Firebase sign-in method
            signInUser(email, password)
        }

        // Handle "Register" click
        newregister.setOnClickListener {
            // Redirect to sign-up activity if user clicks "Register"
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    // Method to sign in the user
    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to new activity (e.g., DashboardActivity)
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign-in fails, display a message to the user
                    Toast.makeText(
                        this,
                        "Login failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}


