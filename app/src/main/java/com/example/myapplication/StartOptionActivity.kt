package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start_option.*

class StartOptionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_option)

        bt_tracker.setOnClickListener(this@StartOptionActivity)

        bt_resources.setOnClickListener(this@StartOptionActivity)

        bt_vaccine.setOnClickListener(this@StartOptionActivity)

        bt_donate.setOnClickListener(this@StartOptionActivity)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                    R.id.bt_tracker -> {
                    val intent = Intent(this@StartOptionActivity, MainActivity::class.java)
                        startActivity(intent)
                }
                R.id.bt_resources -> {
                    val intent = Intent(this@StartOptionActivity, ResourcesActivity::class.java)
                    startActivity(intent)
                }
                R.id.bt_vaccine -> {
                    val intent = Intent(this@StartOptionActivity, VaccineActivity::class.java)
                    startActivity(intent)
                }

                R.id.bt_donate -> {
                    val intent = Intent(this@StartOptionActivity, DonateActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}