package com.example.myapplication.resource_activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_oxygen.*
import java.io.File
import java.lang.Exception


class OxygenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oxygen)

        var csvfileString : String = "oxy_final.csv"
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