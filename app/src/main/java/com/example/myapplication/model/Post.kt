package com.example.myapplication.model

data class Post(
    val profile:Int,
    val username:String,
    val postImageList:List<Int>,
    val description:String,
    val likeBy:List<User>
)

data class User(
    val username:String,
    val profile:Int,
    val storyCount:Int =0,
    val stories:List<Int> = listOf()
)
