package com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThirdScreen ( naController: NavController){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        ImageList(naController)


    }

}


