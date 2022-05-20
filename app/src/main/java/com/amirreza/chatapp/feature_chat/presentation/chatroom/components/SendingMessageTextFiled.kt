package com.amirreza.chatapp.feature_chat.presentation.chatroom.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SendingMessageTextFiled(
    textValue:String,
    onValueChange:(String)->Unit
) {
    Box(Modifier.fillMaxSize()) {
        TextField(
            value = textValue,
            onValueChange = {
                onValueChange(it)
            }
        )
    }
}