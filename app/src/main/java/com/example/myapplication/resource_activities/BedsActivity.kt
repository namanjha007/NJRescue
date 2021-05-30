package com.example.myapplication.resource_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_oxygen.*
import java.lang.Exception

class BedsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beds)

        var csvfileString : String = "beds_final.csv"
        try {
            val bufferReader = application.assets.open(csvfileString).bufferedReader()
            var lines : List<String> = bufferReader.readLines()
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lines)
            list.adapter = adapter
        } catch (e : Exception) {
            Log.e("tag", " $e")
        }
    }
}