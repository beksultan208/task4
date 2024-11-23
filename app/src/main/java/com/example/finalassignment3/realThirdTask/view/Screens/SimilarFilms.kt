package com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assignment3.network.RetrofitInstance.api
import com.example.assignment3.view.SimilarFilmsViewModel

val similarfilmsviewModel= SimilarFilmsViewModel(api)

@Composable
fun SimilarFilmScreen(
    filmId: Int
) {
    val images by similarfilmsviewModel.similar.collectAsState()

    LaunchedEffect(Unit) {
        similarfilmsviewModel.fetchImages(filmId)
    }
     Row (modifier = Modifier.padding(start =26.dp , end  = 26.dp), horizontalArrangement = Arrangement.spacedBy(225.dp)){
         Text(text = "Похожие фильмы")
         Text(text = "${images.size} >")
     }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(images) { imageUrl ->
            Box(
                modifier = Modifier
                    .padding(8.dp).height(156.dp).width(111.dp)

            ) {
                Image(painter = rememberImagePainter(imageUrl.posterUrl), contentDescription = null)
            }
        }}
}