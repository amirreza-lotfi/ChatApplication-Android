package com.amirreza.chatapp.feature_chat.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amirreza.chatapp.ui.theme.Green
import com.amirreza.chatapp.ui.theme.Special
import com.amirreza.chatapp.ui.theme.White

@Composable
fun LoginButton(
    onClick:()->Unit
) {
    Box(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Special)
            .clickable {
                onClick()
            }
    ){
        Text(
            "Log In",
            Modifier.align(Alignment.Center),
            color = White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}