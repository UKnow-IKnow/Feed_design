package com.example.feed_design.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.feed_design.R
import com.example.feed_design.data.api.model.Image


@Composable
fun FeedScreen() {
    val mainViewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by mainViewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier.background(Color.LightGray)
    ) {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(160.dp)
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        items(state) { image: Image ->
            postCard(image = image)
        }
    }
}

@Composable
fun postCard(image: Image) {
    val imagerPainter = rememberImagePainter(data = image.image)
    val likes = (0..100).random()
    val comments = (0..1000).random()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RoundImageCard(
                image = image,
                Modifier
                    .size(48.dp)
                    .padding(4.dp)
            )
            Text(text = image.name, fontWeight = FontWeight.Bold)
        }
        Image(
            painter = imagerPainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(text = image.actor, modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_likes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red),
            )
            Text(text = "$likes likes", modifier = Modifier.padding(start = 8.dp))
        }
        Text(
            text = "$comments comments",
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun RoundImageCard(
    image: Image, modifier: Modifier = Modifier
        .padding(8.dp)
        .size(64.dp)
) {
    val imagerPainter = rememberImagePainter(data = image.image)
    Card(shape = CircleShape, modifier = modifier) {
        Image(
            painter = imagerPainter,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}




