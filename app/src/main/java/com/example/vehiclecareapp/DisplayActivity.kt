package com.example.vehiclecareapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var shopAdapter: ShopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        recyclerView = findViewById(R.id.recyclerView)
        databaseHelper = DatabaseHelper(this)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        loadShops()
    }

        // Load data from SQLite and set adapter

    private fun loadShops() {
        val shops = databaseHelper.getAllShops().toMutableList()
        shopAdapter = ShopAdapter(shops) { shop ->
            shop.id?.let { databaseHelper.deleteShop(it) }  // Delete from database
            Toast.makeText(this, "${shop.name} deleted", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = shopAdapter
    }


}
