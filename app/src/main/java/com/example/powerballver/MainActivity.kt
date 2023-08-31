package com.example.powerballver

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.powerballver.databinding.MainActivityBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : MainActivityBinding

    //볼 초기화하기
    private var comBallData = BallDataModel(
        whiteBall = intArrayOf(0, 0, 0, 0, 0),
        redBall = 0
    )

    private var userBallData = BallDataModel(
        whiteBall = intArrayOf(0, 0, 0, 0, 0),
        redBall = 0
    )

    private var powerMode: Int = 0
    private var powerModeAllocation: Int = 0
    private var modeSet = intent.getIntExtra("playSet",0)

    private var recordComBallData = OutBallDataModel()
    private var recordUserBallData = OutBallDataModel()

    //회전 설정

    private var currentRotationAngle = 0f // 초기 회전 각도
    private val rotationDelta = 360f
    private val rotationDuration = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        modeSetting()
        btnSetting()



    }

    private fun modeSetting(){
        //normal

        Log.d("check", modeSet.toString())

        if(modeSet == 0){
            binding.modeSetCardViewOut.visibility = View.GONE

            ballSetting(comBallData)
            comTextSetting()

            ballSetting(userBallData)
            userTextSetting()

        }
        //double
        else if(modeSet == 1){
            binding.modeSetCardViewOut.visibility = View.GONE

            ballSetting(comBallData)
            comTextSetting()

            ballSetting(userBallData)
            userTextSetting()

        }
        //power 1- 43
        //10배 - 1 ,5배 - 2, 4배 - 4, 3배 - 8, 2배 - 12, 1배 - 16
        else if(modeSet == 2){
            powerMode = Random.nextInt(1, 44)
            binding.modeSetCardViewOut.visibility = View.VISIBLE

            val multi = when(powerMode){
                in 1..16 -> {
                    binding.modeSet.text = 1.toString()
                    powerModeAllocation = 1}
                in 17..28 -> {
                    binding.modeSet.text = 2.toString()
                    powerModeAllocation = 2}
                in 29..36 -> {
                    binding.modeSet.text = 3.toString()
                    powerModeAllocation = 3}
                in 37..40 -> {
                    binding.modeSet.text = 4.toString()
                    powerModeAllocation = 4}
                in 41..42 -> {
                    binding.modeSet.text = 5.toString()
                    powerModeAllocation = 5}
                43 -> {
                    binding.modeSet.text = 10.toString()
                    powerModeAllocation = 10}
                else -> 1.toString()
            }

            ballSetting(comBallData)
            comTextSetting()

            ballSetting(userBallData)
            userTextSetting()
        }
    }
    private fun btnSetting(){
        binding.resetBtn.setOnClickListener{
            binding.userBallSet.visibility = View.VISIBLE
            binding.cumBallSet.visibility = View.VISIBLE
            binding.resetBtn.visibility = View.GONE
        }

        binding.userBallSet.setOnClickListener{
            binding.userBallSet.visibility = View.GONE
            binding.cumBallSet.visibility = View.GONE
            binding.resetBtn.visibility = View.VISIBLE

            ballSetting(userBallData)
            userTextSetting()

            checkBallSetting(comBallData,userBallData)
        }

        binding.cumBallSet.setOnClickListener{
            binding.userBallSet.visibility = View.GONE
            binding.cumBallSet.visibility = View.GONE
            binding.resetBtn.visibility = View.VISIBLE

            rotationsetting()

            ballSetting(comBallData)
            comTextSetting()
            checkBallSetting(comBallData,userBallData)
        }

        binding.mainBottomMenu.setOnItemSelectedListener {
                menuItem ->

            when (menuItem.itemId) {
                R.id.playSetChange -> {
                    val intent = Intent(this@MainActivity, PlaySetActivity::class.java)
                    finish()
                    true
                }
                R.id.totalResult -> {
                    val intent = Intent(this@MainActivity, ResultPageActivity::class.java)

                    intent.putExtra("recordComBallData", recordComBallData)
                    intent.putExtra("recordUserBallData", recordUserBallData)
                    if(modeSet == 1){
                        intent.putExtra("allocation",2)
                    }
                    else if(modeSet == 2){
                        intent.putExtra("allocation",powerModeAllocation)
                    }
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

    }

    //회전 설정
    private fun rotationsetting(){

        val rotateMatchingBall = binding.matchingBall
        currentRotationAngle += rotationDelta
        rotateMatchingBall.animate().rotation(currentRotationAngle).setDuration(rotationDuration).start()

    }

    private fun userTextSetting(){
        binding.userBall1.text = userBallData.whiteBall[0].toString()
        binding.userBall2.text = userBallData.whiteBall[1].toString()
        binding.userBall3.text = userBallData.whiteBall[2].toString()
        binding.userBall4.text = userBallData.whiteBall[3].toString()
        binding.userBall5.text = userBallData.whiteBall[4].toString()
        binding.userBall.text = userBallData.redBall.toString()

        recordUserBallData.whiteBallData.add(userBallData.whiteBall)
        recordUserBallData.redBallData.add(userBallData.redBall)

    }

    private fun comTextSetting(){
        binding.settingBall1.text = comBallData.whiteBall[0].toString()
        binding.settingBall2.text = comBallData.whiteBall[1].toString()
        binding.settingBall3.text = comBallData.whiteBall[2].toString()
        binding.settingBall4.text = comBallData.whiteBall[3].toString()
        binding.settingBall5.text = comBallData.whiteBall[4].toString()
        binding.settingBall.text = comBallData.redBall.toString()

        recordComBallData.whiteBallData.add(comBallData.whiteBall)
        recordComBallData.redBallData.add(comBallData.redBall)
    }

    private fun ballSetting(BallDataSet : BallDataModel){
        // 함수 파라미터는 대부분 읽기 전용 이므로
        // apply를 사용해야한다

        BallDataSet.apply {
            val whiteBallSet = mutableSetOf<Int>()
            while (whiteBallSet.size < 5) {
                val randomNum = Random.nextInt(1, 70)
                whiteBallSet.add(randomNum)
            }
            whiteBall = whiteBallSet.toIntArray()

            val redBallSet = mutableSetOf<Int>()
            while (redBallSet.size < 1) {
                val randomNum = Random.nextInt(1, 27)
                redBallSet.add(randomNum)
            }
            redBall = redBallSet.first()
        }
    }

    // 순서에 따라 색만 변한다

    private fun checkBallSetting(com: BallDataModel, user: BallDataModel) {

    var whiteTimes = 0

        if (com.redBall == user.redBall) {
            binding.userBall.setBackgroundResource(R.color.ballColor)
            recordUserBallData.redTimes.add(1)
        }
        else{binding.userBall.setBackgroundResource(R.color.TransparentGray)
            recordUserBallData.redTimes.add(0)
        }

        for (j in user.whiteBall.indices) {
            val userBallView = when (j) {
                0 -> binding.userBall1
                1 -> binding.userBall2
                2 -> binding.userBall3
                3 -> binding.userBall4
                4 -> binding.userBall5
                else -> null
            }
            if (userBallView != null) {
                if (com.whiteBall.contains(user.whiteBall[j])) {
                    val colorIndex = com.whiteBall.indexOf(user.whiteBall[j]) + 1
                    val colorResId = resources.getIdentifier("ballColor$colorIndex", "color", packageName)
                    Log.d("ballColor", "ballColor$colorIndex")
                    userBallView.setBackgroundResource(colorResId)

                    whiteTimes += 1
                } else {
                    userBallView.setBackgroundResource(R.color.TransparentGray)
                }

            }
        }
        recordUserBallData.whiteTimes.add(whiteTimes)


    }

}