package com.newsify


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.net.PasswordAuthentication

class SQLiteHelper (private val context: Context?):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "create table $TABLE_NAME($ENO text, $NAME text, $ADDRESS text)"
        Toast.makeText(context, "onCreate is called", Toast.LENGTH_LONG).show()
        try {

            db?.execSQL(CREATE_TABLE)
            Toast.makeText(context, "Table is inserted", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Exception $e", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show()
        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(
        no:String?,
        name: String?,
        address: String?
    ): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(ENO, no)
        cv.put(NAME, name)
        cv.put(ADDRESS,  address)
        val result = db.insert(TABLE_NAME, null, cv)
        return result != -1L
    }
    /*  fun deleteData(

          firstName: String?,
          lastName: String?,
          userName: String?,
          password: String?

      ): Boolean {
          val db = writableDatabase
          db.delete(TABLE_NAME, SQLiteHelper.FIRST_NAME + "='" + firstName + "'", null)

          return false
      }
  */
    fun showAll(): Cursor {
        val select = "select *from $TABLE_NAME"

        val db = readableDatabase
        return db.rawQuery(select, null)
    }


    companion object {
        private const val DATABASE_NAME = "emp_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "customer"
        private const val ENO = "eno"
        private const val NAME = "name"
        private const val ADDRESS = "address"
    }
    init {
        Toast.makeText(context,"constructor is called",Toast.LENGTH_SHORT).show()
    }
}