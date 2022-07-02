package com.example.feed_design.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.feed_design.R
import com.example.feed_design.data.api.model.Image
import com.example.feed_design.ui.theme.LightBlue
import com.example.feed_design.ui.theme.OffWhite


@Composable
fun FeedScreen() {
    val mainViewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by mainViewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier.background(OffWhite),
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
    val hour = (1..23).random()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier.weight(0.5f), horizontalArrangement = Arrangement.SpaceBetween) {
                RoundImageCard(
                    image = image,
                    Modifier
                        .size(48.dp)
                        .padding(4.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(1f)

                ) {
                    Text(text = image.name, fontWeight = FontWeight.Bold)
                    Text(text = "$hour hour ago")
                }
                Column(
                    Modifier
                        .align(CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = image.ancestry,
                        modifier = Modifier
                            .background(LightBlue)
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_verticalmenu),
                contentDescription = ""
            )
        }
        Text(text = image.actor, modifier = Modifier.padding(8.dp))
        Image(
            painter = imagerPainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_likes),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Red),
                )
                Text(text = "$likes likes", modifier = Modifier.padding(start = 8.dp))
            }

            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cmt),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                )
                Text(
                    text = "$comments comments",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                )
                Text(
                    text = "share",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
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



//Dummy Screens
@Composable
fun BazaarScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "bazaar",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}




