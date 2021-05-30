package com.example.myapplication

import android.graphics.Color.green
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import com.robinhood.ticker.TickerUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val BASE_URL = "https://api.covid19india.org/"
class MainActivity : AppCompatActivity() {
    private lateinit var currentlyData: List<CovidData>
    private lateinit var adapter: CreateSparkAdapter
    private lateinit var nationalDData: List<CovidData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gsonn = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
        val retro = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonn))
            .build()
        val covidService = retro.create(CovidService::class.java)
        //Fetch National Data
        covidService.getNationData().enqueue(object: Callback<NationWise>{
            override fun onFailure(call: Call<NationWise>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<NationWise>,
                response: Response<NationWise>
            ) {
                val nD = response.body()
                val nationalData = nD?.cases_time_series ?: return
                setUpEventListener()
                nationalDData = nationalData
                updateDisplayWithData(nationalDData)
            }
        })
    }

    private fun setUpEventListener() {
        metricTextView.setCharacterLists(TickerUtils.provideNumberList())

        //Add a listener for scrubing on the chart
        sparkyView.isScrubEnabled = true
        sparkyView.setScrubListener { itemData ->
            if(itemData is CovidData){
                updateInfoForDate(itemData)
            }
        }
        //Respond to radio button selected events
        radioGroupTimeSelection.setOnCheckedChangeListener{ _, checkedId ->
            adapter.daysago = when (checkedId) {
                R.id.radiooWeek -> TimeScale.WEEK
                R.id.radiooMonth -> TimeScale.MONTH
                else -> TimeScale.MAX
            }
            adapter.notifyDataSetChanged()
        }
        radioMetricSelection.setOnCheckedChangeListener{ _, checkedId ->
            when(checkedId){
                R.id.radiooNegative -> updateDisplayMetric(Metric.NEGATIVE)
                R.id.radiooPositive -> updateDisplayMetric(Metric.POSITIVE)
                R.id.radiooDeath -> updateDisplayMetric(Metric.DEATH)
            }
        }
    }

    private fun updateDisplayMetric(metric: Metric) {
        //Update the color of the chart
        val colorRes = when(metric) {
            Metric.NEGATIVE -> R.color.neg
            Metric.POSITIVE -> R.color.pos
            Metric.DEATH -> R.color.death
        }
        val colorInt = ContextCompat.getColor(this, colorRes)
        sparkyView.lineColor = colorInt
        metricTextView.setTextColor(colorInt)

        //Update the metric on the adpater
        adapter.metric = metric
        adapter.notifyDataSetChanged()

        //Reset number and date shown in the bottom text views
        updateInfoForDate(currentlyData.last())
    }

    private fun updateDisplayWithData(dailyData: List<CovidData>) {
        currentlyData = dailyData
        //Create a new SparkAdapter with the data
        adapter = CreateSparkAdapter(dailyData)
        sparkyView.adapter = adapter
        // Update Radio buttons to select the positive cases and max time by default
        radiooPositive.isChecked = true
        radiooMax.isChecked = true
        // Display metric for the most recent date
        updateDisplayMetric(Metric.POSITIVE)
    }

    private fun updateInfoForDate(covidData: CovidData) {

        val numCases = when (adapter.metric) {
            Metric.NEGATIVE -> covidData.dailyrecovered
            Metric.POSITIVE -> covidData.dailyconfirmed
            Metric.DEATH -> covidData.dailydeceased
        }
        metricTextView.text = NumberFormat.getInstance().format(numCases)
        val outputDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        dateTv.text = outputDateFormat.format(covidData.dateymd)
    }
}