package com.example.lab1.service

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.lab1.model.LogStatistic

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "statistics"
val COL_TIME = "time"
val COL_SATIETY = "satiety"
val COL_ID = "id"

class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SATIETY + " INTEGER, " +
                COL_TIME + " VARCHAR(64))";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

//    fun insertData(log : LogStatistic) {
//        val db = this.writableDatabase
//        var cv = ContentValues()
//        cv.put(COL_TIME, log.time)
//        cv.put(COL_SATIETY, log.satiety)
//        var result = db.insert(TABLE_NAME, null, cv)
//        if (result == -1.toLong())
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//        else
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
//    }
}