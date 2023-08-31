package com.example.powerballver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.powerballver.databinding.MainActivityBinding
import com.example.powerballver.databinding.ResultPageActivityBinding

class ResultPageActivity : AppCompatActivity() {

    private lateinit var binding : ResultPageActivityBinding

    // 값 받아 오기
    private val recordComBallData = intent.getParcelableExtra<OutBallDataModel>("recordComBallData")
    private val recordUserBallData = intent.getParcelableExtra<OutBallDataModel>("recordUserBallData")
    private val allocation = intent.getIntExtra("allocation",0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultPageActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





        val linechart = binding.lineChart
        val xAXIS = linechart.xAxis
    }

    private fun lineChartSetting(){

    }

    private fun priceSetting(){

        var size = recordUserBallData!!.redBallData.size


    }

}