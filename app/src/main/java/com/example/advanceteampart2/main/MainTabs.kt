package com.example.advanceteampart2.main

import androidx.fragment.app.Fragment

data class MainTabs (
    val fragment : Fragment,        //fragment
    val titleResource : Int         //tab에 대한 제목
)