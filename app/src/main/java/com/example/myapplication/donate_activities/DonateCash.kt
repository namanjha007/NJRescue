package com.example.myapplication.donate_activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_donate_cash.*

class DonateCash : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate_cash)

        d1.setOnClickListener(this@DonateCash)
        d2.setOnClickListener(this@DonateCash)
        d3.setOnClickListener(this@DonateCash)
        d4.setOnClickListener(this@DonateCash)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.d1 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://goonj.org/support-covid-19-affected/"))
                    startActivity(intent)
                }
                R.id.d2 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.impactguru.com/fundraiser/oxygen"))
                    startActivity(intent)
                }

                R.id.d3 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cry.org/covid-second-wave-direct-response/?utm_source=referral&utm_medium=referral&utm_campaign=61674&utm_content=TPF-listing-covid19relief"))
                    startActivity(intent)
                }
                R.id.d4 -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://coviddonors.org/payments/eeded9/new"))
                    startActivity(intent)
                }
            }
        }
    }
}