package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.donate_activities.DonateCash
import com.example.myapplication.donate_activities.DonatePlasma
import kotlinx.android.synthetic.main.activity_donate.*

class DonateActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        bt_plasma.setOnClickListener(this@DonateActivity)

        bt_cash.setOnClickListener(this@DonateActivity)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.bt_plasma -> {
                    val intent = Intent(this@DonateActivity, DonatePlasma::class.java)
                    startActivity(intent)
                }
                R.id.bt_cash -> {
                    val intent = Intent(this@DonateActivity, DonateCash::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}