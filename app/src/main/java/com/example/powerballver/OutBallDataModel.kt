package com.example.powerballver

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OutBallDataModel(
    val whiteBallData: MutableList<IntArray> = mutableListOf(),
    val redBallData: MutableList<Int> = mutableListOf(),
    val whiteTimes: MutableList<Int> = mutableListOf(),
    val redTimes: MutableList<Int> = mutableListOf()
): Parcelable
