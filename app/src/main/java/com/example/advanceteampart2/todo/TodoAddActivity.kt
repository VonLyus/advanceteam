package com.example.advanceteampart2.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.advanceteampart2.databinding.TodoAddActivityBinding

class TodoAddActivity : AppCompatActivity(){

    private lateinit var binding: TodoAddActivityBinding


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = TodoAddActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        textSet()
    }

    private fun textSet(){
        binding.registerBtn.setOnClickListener{
            val userTitleInput = binding.editTitle.text.toString()
            val userCommentInput = binding.editComment.text.toString()
            val intent = Intent()
            intent.putExtra("titleValue", userTitleInput)
            intent.putExtra("commentValue", userCommentInput)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }
}