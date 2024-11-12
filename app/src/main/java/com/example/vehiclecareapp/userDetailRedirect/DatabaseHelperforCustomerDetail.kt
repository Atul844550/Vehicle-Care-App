package com.example.vehiclecareapp.userDetailRedirect

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperforCustomerDetail(context: Context) : SQLiteOpenHelper(context, "CustomerDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CustomerDetails (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, mobile TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS CustomerDetails")
        onCreate(db)
    }

    fun insertCustomerDetails(name: String, address: String, mobile: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("mobile", mobile)

        val result = db.insert("CustomerDetails", null, contentValues)
        return result != -1L // Return true if insertion was successful
    }
}