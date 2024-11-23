package com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalassignment3.realThirdTask.realThirdTask.model.DataClass


@Composable
fun LazyRoww(item:List<DataClass>, index:Int, navController: NavController) {

    Column  (modifier = Modifier , verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Card(modifier = Modifier.width(111.dp).height(156.dp).clickable { navController.navigate("NewScreen3") }) {
            Image(painter = painterResource(id= item[index].image), contentDescription = null, modifier = Modifier.fillMaxSize())}
        Column(modifier = Modifier.height(30.dp)) {
            Text(
                text = item[index].title,
               style = TextStyle(fontSize = 14.sp)
            )
         Text(
                text = item[index].descrip,
                style = TextStyle(fontSize = 12.sp,  color = Color.Gray)
            )

        }
    }
}
