package com.amirreza.chatapp.feature_chat.presentation.chatroom.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feature_chat.domain.entity.Message
import java.time.LocalDateTime

@Composable
fun ChatList(
    listModifier: Modifier,
    chats:List<Message>,
    username:String
) {
    LazyColumn(
        modifier = listModifier,
        reverseLayout = true
    ){
        items(chats){ message ->
            ChatItem(username, message)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Test() {

    val text = "hi every body. welcome to this chat application that you can easily comminucate with " +
            "your family and friends. enjoy your time. good luck !!"

    Box(Modifier.fillMaxSize()){
        val list = listOf(
            Message("text", "amirreza", LocalDateTime.now().toString()),
            Message(text, "ali", LocalDateTime.now().toString()),
            Message(text, "ahmad", LocalDateTime.now().toString()),
            Message(text, "amirreza", LocalDateTime.now().toString()),
            Message(text, "reza", LocalDateTime.now().toString()),
            Message(text, "jamshid", LocalDateTime.now().toString()),
        )
        ChatList(listModifier = Modifier.fillMaxSize(), chats = list, username = "amirreza")
    }
}
