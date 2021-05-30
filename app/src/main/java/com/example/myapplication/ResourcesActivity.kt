package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.resource_activities.*
import kotlinx.android.synthetic.main.activity_resources2.*
import java.io.File

class ResourcesActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources2)

        bt_oxygen.setOnClickListener(this@ResourcesActivity)
        bt_ambulance.setOnClickListener(this@ResourcesActivity)
        bt_beds.setOnClickListener(this@ResourcesActivity)
        bt_medical.setOnClickListener(this@ResourcesActivity)
        bt_misc.setOnClickListener(this@ResourcesActivity)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.bt_oxygen -> {
                    val intent = Intent(this@ResourcesActivity, OxygenActivity::class.java)
                    startActivity(intent)
                }

                R.id.bt_ambulance -> {
                    val intent = Intent(this@ResourcesActivity, AmbulanceActivity::class.java)
                    startActivity(intent)
                }

                R.id.bt_beds -> {
                    val intent = Intent(this@ResourcesActivity, BedsActivity::class.java)
                    startActivity(intent)
                }

                R.id.bt_medical -> {
                    val intent = Intent(this@ResourcesActivity, MedicineActivity::class.java)
                    startActivity(intent)
                }

                R.id.bt_misc -> {
                    val intent = Intent(this@ResourcesActivity, MiscellaneousActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}