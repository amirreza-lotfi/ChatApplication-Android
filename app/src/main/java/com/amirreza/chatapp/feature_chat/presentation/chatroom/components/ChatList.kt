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
    Box(modifier = listModifier){
        LazyColumn(
            reverseLayout = true
        ) {
            items(chats) { message ->
                ChatItem(username, message)
            }
        }
    }
}

