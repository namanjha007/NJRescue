package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_vaccine.*
import org.json.JSONException
import java.util.*

class VaccineActivity : AppCompatActivity() {
    private lateinit var centerList: MutableList<CenterRvModel>
    private lateinit var centerRVAdapter: CenterRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine)



        centerList = mutableListOf()

        center_list_recycler_view.layoutManager = LinearLayoutManager(this)


        btn_search.setOnClickListener {
            closeKeyboard()
            val pinCode = et_pin_code.text.toString()
            if (pinCode.length != 6) {
                et_pin_code.error = "Please enter a valid pin-code"
            } else {
                centerList.clear()

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                    this, { _, year, monthOfYear, dayOfMonth ->
                        val dateStr = """$dayOfMonth-${monthOfYear + 1}-${year}"""

                        getAppointment(pinCode, dateStr)
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        }

    }

    private fun getAppointment(pinCode: String, date: String) {
        val url =
            "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=$pinCode&date=$date"

        val queue = Volley.newRequestQueue(this@VaccineActivity)

        val request = JsonObjectRequest(com.android.volley.Request.Method.GET, url, null, { response ->
            Log.e("request", "Success response is $response")
            try {
                val centerArray = response.getJSONArray("centers")
                if (centerArray.length() == 0) {
                    Toast.makeText(this, "No centers available", Toast.LENGTH_SHORT).show()
                }
                for (i in 0 until centerArray.length()) {
                    val centerObj = centerArray.getJSONObject(i)
                    val centerName = centerObj.getString("name")
                    val centerAddress = centerObj.getString("address")
                    val centerFromTime = centerObj.getString("from")
                    val centerToTime = centerObj.getString("to")
                    val feeType = centerObj.getString("fee_type")

                    val sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0)
                    val ageLimit = sessionObj.getInt("min_age_limit")
                    val vaccineName = sessionObj.getString("vaccine")
                    val availableCapacity = sessionObj.getInt("available_capacity")

                    val center = CenterRvModel(
                        centerName,
                        centerAddress,
                        centerFromTime,
                        centerToTime,
                        feeType,
                        ageLimit,
                        vaccineName,
                        availableCapacity
                    )
                    centerList.add(center)
                }
                centerRVAdapter = CenterRVAdapter(centerList)
                center_list_recycler_view.adapter = centerRVAdapter
                center_list_recycler_view.setHasFixedSize(true)
                centerRVAdapter.notifyDataSetChanged()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
            Log.e("Error", "Response is $error")
            Toast.makeText(this@VaccineActivity, "Failed to get response", Toast.LENGTH_SHORT).show()
        })
        queue.add(request)
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}