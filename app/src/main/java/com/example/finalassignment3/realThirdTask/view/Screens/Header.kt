package com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun Header (){
    Row (modifier = Modifier
        .padding( start = 26.dp)
        .width(308.dp)
        .height(18.24.dp),  verticalAlignment = Alignment.CenterVertically
        ,  horizontalArrangement = Arrangement.SpaceBetween){
        Row(modifier = Modifier
            .width(120.dp)
            .height(18.24.dp)
            .fillMaxSize() , horizontalArrangement = Arrangement.Start) {
            Image(painter = painterResource(id= com.example.finalassignment3.R.drawable.vse), contentDescription = "", modifier = Modifier.fillMaxSize())

        }

    }
}