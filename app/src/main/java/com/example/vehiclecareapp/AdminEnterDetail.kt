package com.example.vehiclecareapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts

class AdminEnterDetail : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST=1



    private lateinit var shopImage: ImageView
    private lateinit var shopName: EditText
    private lateinit var shopAddress: EditText
    private lateinit var shopMobile: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var submitButton: Button

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_enter_detail)

        shopImage = findViewById(R.id.shopImage)
        shopName = findViewById(R.id.shopName)
        shopAddress = findViewById(R.id.shopaddress)
        shopMobile = findViewById(R.id.shopmobile)
        ratingBar = findViewById(R.id.ratingBar)
        submitButton = findViewById(R.id.submitButton)

        // Open image picker when shop image is clicked
        shopImage.setOnClickListener {
            openImagePicker()
        }




        submitButton.setOnClickListener {
            submitData()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data!! // Get the URI of the selected image
            // Optionally display the image in an ImageView
            // imageView.setImageURI(imageUri)
        }
    }

    private fun submitData() {
        val name = shopName.text.toString()
        val address = shopAddress.text.toString()
        val mobile = shopMobile.text.toString()
        val rating = ratingBar.rating

        // Convert URI to String (path) if necessary
        val imagePath = imageUri.toString() // Use the image URI directly

        // Insert data into the database
        val dbHelper = DatabaseHelper(this)
        dbHelper.insertShop(name, address, mobile, rating, imagePath)

        // Redirect to RecyclerView Activity
        val intent = Intent(this, DisplayActivity::class.java)
        startActivity(intent)
    }




}


