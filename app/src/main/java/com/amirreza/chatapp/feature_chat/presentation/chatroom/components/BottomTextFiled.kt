package com.amirreza.chatapp.feature_chat.presentation.chatroom.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout



@ExperimentalComposeUiApi
@Composable
fun BottomTextFiled(
    modifier: Modifier,
    value:String,
    onValueChange:(String)->Unit,
    onSendClicked:()->Unit
) {

    ConstraintLayout(modifier.padding(start = 16.dp, end = 8.dp)) {
        val (messageRef, sendRef) = createRefs()

        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = { Text("Message ...") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
            modifier = Modifier.constrainAs(ConstrainedLayoutReference(messageRef)) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )

        Icon(
            Icons.Rounded.Send,
            contentDescription = "send",
            Modifier
                .clickable {
                    onSendClicked()
                }
                .constrainAs(ConstrainedLayoutReference(sendRef)) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }

        )

    }
}