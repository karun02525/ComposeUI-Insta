package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.myapplication.R
import com.example.myapplication.model.Post
import com.example.myapplication.model.Stories
import com.example.myapplication.model.User
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolBar()
        StoriesSection(storyList = getStories())
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .height(1.dp)
        )
        PostSection(Modifier.fillMaxWidth(), getPostData())
    }
}


@Composable
fun PostSection(modifier: Modifier, postList: List<Post>) {

    LazyColumn() {
        items(postList) {
            PostItem(modifier, it)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostItem(modifier: Modifier = Modifier, post: Post) {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {

        Box(
            modifier = modifier.fillMaxWidth()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.TopStart)
            ) {
                Image(
                    painter = painterResource(id = post.profile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = post.username,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .width(100.dp)
                        .padding(start = 12.dp),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )

            }

            Icon(
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = "more",
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
            )
        }


        PostCarousal(post.postImageList, pagerState)

        PostContent(pagerState)

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostContent(pagerState: PagerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(imageVector = Icons.Default.Favorite, "")
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.Message, "")
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.AddAPhoto, "")
        }

        if (pagerState.currentPage > 1) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.Blue,
                inactiveColor = Color.Gray,
                modifier = Modifier.align(Center)
            )
        }
        Icon(
            imageVector = Icons.Default.Bookmarks, "",
            modifier = Modifier.align(Alignment.CenterEnd)
        )

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostCarousal(postImageList: List<Int>, pagerState: PagerState) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            count = postImageList.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { currentPage ->
            Image(
                painter = painterResource(id = postImageList[currentPage]),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(375.dp),
                contentScale = ContentScale.Crop
            )
        }
        if(pagerState.pageCount>1) {
            NudgeCount(
                modifier = Modifier.align(TopEnd).padding(end = 10.dp, top = 10.dp),
                pagerState
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NudgeCount(modifier: Modifier = Modifier, pagerState: PagerState) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.Black.copy(0.4f))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text = "${pagerState.currentPage + 1}", color = Color.White)
        Text(text = "/", color = Color.White)
        Text(text = "${pagerState.pageCount}", color = Color.White)
    }

}


@Composable
fun AppToolBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.insta_logo), contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .align(Alignment.TopStart)
        )

        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(imageVector = Icons.Default.Favorite, "")
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.Message, "")
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.AddTask, "")
        }

    }
}

@Composable
fun StoriesSection(modifier: Modifier = Modifier, storyList: List<Stories>) {
    LazyRow {
        items(storyList) {
            StoryItem(modifier, it)
        }
    }
}

@Composable
fun StoryItem(modifier: Modifier, item: Stories) {
    Column(modifier = modifier.padding(5.dp)) {
        Image(
            painter = painterResource(id = item.profile),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .border(
                    width = 2.dp, brush = Brush.linearGradient(
                        listOf(
                            Color("#DE0046".toColorInt()),
                            Color("#F7A34B".toColorInt()),
                        )
                    ),
                    shape = CircleShape
                )
                .padding(5.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = item.username,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(60.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewTopBar() {

    Column {
        //  AppToolBar()
        //StoriesSection(storyList = getStories())
        //  PostItem(post = getPostData()[0])
        // NudgeCount()
    }

}


fun getStories(): List<Stories> = listOf(
    Stories("karun", R.drawable.pic1),
    Stories("Ram Sjio", R.drawable.pic2),
    Stories("Roan", R.drawable.pic3),
    Stories("Mila", R.drawable.pic4),
    Stories("Raml", R.drawable.pic5),
    Stories("Gime", R.drawable.pic6),
    Stories("Sorn", R.drawable.pic7),
)

fun getPostData(): List<Post> = listOf(
    Post(
        username = "Karun",
        profile = R.drawable.pic1,
        postImageList = listOf(R.drawable.pic6),
        description = "this nice post",
        likeBy = listOf(
            User(profile = R.drawable.pic2, username = "Moja"),
            User(profile = R.drawable.pic5, username = "Ram Sinfg")
        )
    ),
    Post(
        username = "Mukesh",
        profile = R.drawable.pic2,
        postImageList = listOf(R.drawable.pic3, R.drawable.pic1),
        description = "ieu nice IOS",
        likeBy = listOf(
            User(profile = R.drawable.pic2, username = "Osnh"),
            User(profile = R.drawable.pic1, username = "PdR Sifnb"),
            User(profile = R.drawable.pic7, username = "PdR Sifnb")
        )
    ),
    Post(
        username = "Rakdo",
        profile = R.drawable.pic1,
        postImageList = listOf(R.drawable.pic3, R.drawable.pic1, R.drawable.pic5),
        description = "ieu nice IOS",
        likeBy = listOf(
            User(profile = R.drawable.pic2, username = "Osnh"),
            User(profile = R.drawable.pic1, username = "PdR Sifnb"),
            User(profile = R.drawable.pic7, username = "PdR Sifnb"),
            User(profile = R.drawable.pic1, username = "PdR RRW")
        )
    )
)

