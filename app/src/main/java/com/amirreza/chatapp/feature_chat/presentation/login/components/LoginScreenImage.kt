package com.amirreza.chatapp.feature_chat.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amirreza.chatapp.R

@Composable
fun LoginScreenImage() {
    Box(
        Modifier.fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "login screen",
            Modifier
                .width(177.dp)
                .height(158.dp)
                .align(Alignment.Center)
        )
    }
}