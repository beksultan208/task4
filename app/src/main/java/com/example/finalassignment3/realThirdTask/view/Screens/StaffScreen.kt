package com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.assignment3.view.StaffViewModel
import com.example.finalassignment3.realThirdTask.realThirdTask.model.Staff

@Composable
fun StaffListScreen(staffViewModel: StaffViewModel = viewModel(), kinopoiskid: Int, navController: NavController) {
    val fulstaffList by staffViewModel.staffList.collectAsState(emptyList())

    val staffList = fulstaffList.filter { it.professionKey == "ACTOR" }
    val otherStaffList = fulstaffList.filter { it.professionKey != "ACTOR" }
    val actorRows = (0 until staffList.size step 4).toList()
    LaunchedEffect(Unit) {
        staffViewModel.fetchStaff(kinopoiskid)
        staffViewModel.staffList.collect { staff ->
            val limitedStaff = staff.take(20)
        }
    }


    Column (modifier = Modifier.padding(start =26.dp,end  = 26.dp)){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "В фильме снимались")
            Text(text = "${staffList.size} >")
        }
        LazyRow {
            items(actorRows) { id ->
                StaffInfo(staffList, id, navController)
            }
        }


    }
}

@Composable
fun FullStaffList(staffViewModel: StaffViewModel = viewModel(), kinopoiskid: Int) {
    val fulstaffList by staffViewModel.staffList.collectAsState(emptyList())

    val otherStaffList = fulstaffList.filter { it.professionKey != "ACTOR" }

    val otherStaffRows = (0 until otherStaffList.size step 2).toList()

    Column (){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "В фильме снимались")
            Text(text = "${otherStaffList.size} >")
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(otherStaffRows) { id ->
                FullStaffInfo(otherStaffList, id)
            }
        }
    }
}

@Composable
fun StaffInfo(staffList: List<Staff>, id: Int, navController: NavController) {
    Column(modifier = Modifier.height(296.dp).width(207.dp)) {
        for (i in id until (id + 4)) {
            if (i < staffList.size) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp).clickable { navController.navigate("ActorPage/${staffList[i].staffId}")}) {
                    Card(modifier = Modifier.width(49.dp).height(68.dp)) {
                        Image(
                            painter = rememberImagePainter(staffList[i].posterUrl),
                            contentDescription = "",
                            modifier = Modifier
                                .height(68.dp)
                                .width(48.dp)
                                .fillMaxSize()
                        )
                    }
                    Column {
                        Text(text = "${staffList[i].nameRu}")
                        Text(text = "${staffList[i].professionKey}")
                    }
                }
            }
        }
    }
}

@Composable
fun FullStaffInfo(staffList: List<Staff>, id: Int) {
    Column(modifier = Modifier.height(148.dp).width(207.dp)) {
        for (i in id until (id + 2)) {
            if (i < staffList.size) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier =  Modifier.padding(top = 8.dp)) {
                    Card(modifier = Modifier.width(49.dp).height(68.dp)) {
                        Image(
                            painter = rememberImagePainter(staffList[i].posterUrl),
                            contentDescription = "",
                            modifier = Modifier
                                .height(68.dp)
                                .width(48.dp)
                                .fillMaxSize()
                        )
                    }
                    Column {
                    Text(text = "${staffList[i].nameRu}")
                        Text(text = "${staffList[i].professionKey}")
                }
            }
        }
    }
}}



@Composable
fun LastRow(staffList: List<Staff>, id: Int, remainingItems: Int) {
    Column(modifier = Modifier.height(300.dp).width(207.dp)) {

    for (i in id until (id + remainingItems)) {
        if (i < staffList.size) {
                Row {
                    Image(
                        painter = rememberImagePainter(staffList[i].posterUrl),
                        contentDescription = "",
                        modifier = Modifier.height(68.dp).width(48.dp).fillMaxSize()
                    )
                    Text(text = "${staffList[i].nameRu}")
                }
            }
        }
    }
}
