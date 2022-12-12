package com.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.newsify.SQLiteHelper
import com.sqlitedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onclick()
    }

    private fun onclick() {
        db = SQLiteHelper(this)
        binding.submit.setOnClickListener {
            val No = binding.etEno.text.toString()
            val Name = binding.etEname.text.toString()
            val Address = binding.etEaddress.text.toString()
            if (db.insertData(No,Name,Address))
            {
                Toast.makeText(this,"Record is Stored",Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this,"Record does not Stored",Toast.LENGTH_SHORT).show()

            }
        }
    }
}