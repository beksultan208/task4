package com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalassignment3.realThirdTask.realThirdTask.model.DataClass


@Composable
fun ListImages(item: List<DataClass>, index:Int, navController: NavController) {
    Column (modifier = Modifier.height(238.dp)){
        Row (modifier = Modifier.padding(start= 26.dp).height(20.dp).width(308.dp), horizontalArrangement = Arrangement.Absolute.SpaceBetween){
        Text(text = item[index].titles, style = TextStyle(fontSize = 18.sp,  fontWeight = FontWeight.W600), modifier = Modifier.clickable {      })


            Text(text = "Все", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W500, color = Color.Blue ))
        }
        LazyRow(
            modifier = Modifier.padding(start= 26.dp).padding(top= 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        {

            items(6) { index ->
                LazyRoww(item=item, index=index,navController)
            }
        }

    }
}