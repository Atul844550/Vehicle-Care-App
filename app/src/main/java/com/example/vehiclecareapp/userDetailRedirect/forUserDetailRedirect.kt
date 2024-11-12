package com.example.vehiclecareapp.userDetailRedirect

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclecareapp.DatabaseHelper
import com.example.vehiclecareapp.R

class forUserDetailRedirect : AppCompatActivity() {

    private lateinit var textname: EditText
    private lateinit var textAddress: EditText
    private lateinit var textNumber: EditText
    private lateinit var customerdetail: Button
    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var number: TextView

    private lateinit var dbHelper: DatabaseHelperforCustomerDetail

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_user_detail_redirect)

        // Initialize views
        textname = findViewById(R.id.editTextName)
        textAddress = findViewById(R.id.editTextAddress)
        textNumber = findViewById(R.id.editTextMobile)
        name = findViewById(R.id.customername)
        address = findViewById(R.id.customerAddress)
        number = findViewById(R.id.customerNumber)
        customerdetail = findViewById(R.id.submitButton)

        // Initialize SQLite helper
        dbHelper = DatabaseHelperforCustomerDetail(this)

        // Set click listener
        customerdetail.setOnClickListener {
            val enteredName = textname.text.toString()
            val enteredAddress = textAddress.text.toString()
            val enteredNumber = textNumber.text.toString()

            // Validate input
            if (enteredName.isEmpty() || enteredAddress.isEmpty() || enteredNumber.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                // Save to database
                val isInserted = dbHelper.insertCustomerDetails(enteredName, enteredAddress, enteredNumber)

                if (isInserted) {
                    // Display details in TextView
                    name.text = "Name: $enteredName"
                    address.text = "Address: $enteredAddress"
                    number.text = "Number: $enteredNumber"

                    Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_SHORT).show()

                    // Clear EditText fields
                    textname.text.clear()
                    textAddress.text.clear()
                    textNumber.text.clear()
                } else {
                    Toast.makeText(this, "Failed to save details", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
