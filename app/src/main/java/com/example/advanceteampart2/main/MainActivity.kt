package com.example.advanceteampart2.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.example.advanceteampart2.R
import com.example.advanceteampart2.databinding.MainActivityBinding
import com.example.advanceteampart2.todo.TodoAddActivity
import com.example.advanceteampart2.todo.TodoFragment
import com.example.advanceteampart2.todo.TodoModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding : MainActivityBinding

    private var titleValue : String? = ""
    private var commentValue : String? = ""

    private val viewPagerAdapter by lazy{
        MainViewPagerAdapter(this@MainActivity)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            titleValue = data?.getStringExtra("titleValue")
            commentValue = data?.getStringExtra("commentValue")

            (viewPagerAdapter.getFragment(0) as TodoFragment).addTodoListSetting(titleValue, commentValue)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.fabAddTodo.show()
                } else {
                    binding.fabAddTodo.hide()
                }
            }
        })

    }


    private fun initView() = with(binding) {

        toolBar.title = getString(R.string.app_name)

        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) {
                tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()


        fabAddTodo.setOnClickListener {
            val intent = Intent(this@MainActivity, TodoAddActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

}