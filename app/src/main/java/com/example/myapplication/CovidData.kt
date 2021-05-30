package com.example.myapplication

import java.util.*

data class CovidData (
    val dateymd: Date,
    val dailyconfirmed: Int,
    val dailyrecovered: Int,
    val dailydeceased: Int,
)