package com.example.myapplication

import android.graphics.RectF
import com.robinhood.spark.SparkAdapter

class CreateSparkAdapter(private val dailyData: List<CovidData>) : SparkAdapter() {
    var metric = Metric.POSITIVE
    var daysago = TimeScale.MAX
    override fun getY(index: Int): Float {
        val chosenDayDate = dailyData[index]
        return when (metric) {
            Metric.NEGATIVE -> chosenDayDate.dailyrecovered.toFloat()
            Metric.POSITIVE -> chosenDayDate.dailyconfirmed.toFloat()
            Metric.DEATH -> chosenDayDate.dailydeceased.toFloat()
        }
    }

    override fun getItem(index: Int) = dailyData[index]

    override fun getCount() = dailyData.size

    override fun getDataBounds(): RectF {
        val bounds = super.getDataBounds()
        if(daysago != TimeScale.MAX) {
            bounds.left = count - daysago.numDays.toFloat()
        }
        return bounds
    }
}
