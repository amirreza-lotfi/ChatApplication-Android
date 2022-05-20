package com.feature_chat.domain.entity

data class Message(
    val text:String,
    val username:String,
    val formattedTime:String,
)
