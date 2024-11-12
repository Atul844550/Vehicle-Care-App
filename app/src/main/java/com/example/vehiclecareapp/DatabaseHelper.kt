package com.example.vehiclecareapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "shop.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "shops"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_MOBILE = "mobile"
        const val COLUMN_RATING = "rating"
        const val COLUMN_IMAGE = "image" // New column for image
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_ADDRESS TEXT, "
                + "$COLUMN_MOBILE TEXT, "
                + "$COLUMN_RATING REAL, "
                + "$COLUMN_IMAGE TEXT)") // Include image column
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Method to insert shop data including image
    fun insertShop(name: String, address: String, mobile: String, rating: Float, imagePath: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_ADDRESS, address)
        contentValues.put(COLUMN_MOBILE, mobile)
        contentValues.put(COLUMN_RATING, rating)
        contentValues.put(COLUMN_IMAGE, imagePath) // Insert image path

        // Insert row and return the row ID
        return db.insert(TABLE_NAME, null, contentValues)
    }

    // Method to retrieve all shops
    fun getAllShops(): List<Shop> {
        val shopList = mutableListOf<Shop>()
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID))
                    val name = it.getString(it.getColumnIndexOrThrow(COLUMN_NAME))
                    val address = it.getString(it.getColumnIndexOrThrow(COLUMN_ADDRESS))
                    val mobile = it.getString(it.getColumnIndexOrThrow(COLUMN_MOBILE))
                    val rating = it.getFloat(it.getColumnIndexOrThrow(COLUMN_RATING))
                    val imagePath = it.getString(it.getColumnIndexOrThrow(COLUMN_IMAGE)) // Get image path

                    val shop = Shop(id, name, address, mobile, rating, imagePath) // Update Shop class constructor
                    shopList.add(shop)
                } while (it.moveToNext())
            }
        }
        return shopList
    }

    fun deleteShop(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete("Shops", "id = ?", arrayOf(id.toString()))
        db.close()
        return result
    }



}

