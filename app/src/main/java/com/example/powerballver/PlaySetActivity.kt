package com.example.powerballver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.powerballver.databinding.MainActivityBinding
import com.example.powerballver.databinding.PlaySetActivityBinding

class PlaySetActivity : AppCompatActivity() {

    private lateinit var binding: PlaySetActivityBinding

    private val backgrounds = listOf(
        R.drawable.back_set_1,
        R.drawable.back_set_2,
        R.drawable.back_set_3,
        R.drawable.back_set_4
    )
    private lateinit var backgroundSetting: ConstraintLayout
    private var currentBackgroundIndex = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlaySetActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        btnSetting()

        // 배경화면 설정
        backgroundSetting = binding.setBackground
        startBackgroundChangeTask()
    }

    private fun btnSetting(){
        binding.normalBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("playSet", 0)
            startActivity(intent)
        }
        binding.doubleBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("playSet", 1)
            startActivity(intent)
        }
        binding.powerBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("playSet", 2)
            startActivity(intent)
        }
    }


    //배경화면 설정
    private fun startBackgroundChangeTask() {
        handler.postDelayed(backgroundChangeRunnable, 3000)
    }
    private val backgroundChangeRunnable = object : Runnable {
        override fun run() {
            changeBackground()
            startBackgroundChangeTask()
        }
    }
    private fun changeBackground() {
        backgroundSetting.setBackgroundResource(backgrounds[currentBackgroundIndex])
        currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.size
    }
    override fun onDestroy() {
        handler.removeCallbacks(backgroundChangeRunnable)
        super.onDestroy()
    }
}