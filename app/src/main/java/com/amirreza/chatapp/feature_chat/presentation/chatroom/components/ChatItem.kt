package com.amirreza.chatapp.feature_chat.presentation.chatroom.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.amirreza.chatapp.ui.theme.Blue
import com.amirreza.chatapp.ui.theme.Green
import com.amirreza.chatapp.ui.theme.White
import com.feature_chat.domain.entity.Message
import java.time.LocalDateTime
import java.util.*






val text = "hi every body. welcome to this chat application that you can easily comminucate with " +
        "your family and friends. enjoy your dateCreated. good luck !!"

@Composable
fun ChatItem(username:String, message: Message){
    val alignmentOfText:Alignment = if (message.username == username){
        Alignment.CenterEnd
    }else{
        Alignment.CenterStart
    }

    val textOfAvatar = try {
        username.subSequence(0,2).toString()
    }catch (e:Exception){
        username.subSequence(0,1).toString()
    }


    if(alignmentOfText == Alignment.CenterStart){
        OtherPeopleChatItem(textOfAvatar = textOfAvatar, message)
    }else{
        UserChatItem(textOfAvatar = textOfAvatar, message)
    }

}


@Composable
fun UserChatItem(textOfAvatar:String, message: Message) {
    val widthOfScreen = LocalConfiguration.current.screenWidthDp.dp

    ConstraintLayout(Modifier.fillMaxWidth().padding(4.dp)) {
        val (avatarRef, spacerRef, messageRef) = createRefs()

        //avatar
        Box(
            Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Blue)
                .constrainAs(avatarRef) {
                    end.linkTo(parent.end)
                    bottom.linkTo(messageRef.bottom)
                },
        ){
            Text(
                text = textOfAvatar.uppercase(Locale.getDefault()),Modifier.align(Alignment.Center),
                color = White
            )
        }

        //spacer
        Box(
            modifier = Modifier
                .width(4.dp)
                .constrainAs(spacerRef) {
                    end.linkTo(avatarRef.start)
                }                .background(Color.Yellow)
            ,
        )

        //text

        Box(
            modifier = Modifier
                .constrainAs(messageRef) {
                    end.linkTo(spacerRef.start)
                },
        ) {
            Column(
                Modifier
                    .align(Alignment.CenterStart)
                    .width(widthOfScreen / 2)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Blue)
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            ) {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = message.username,
                        Modifier.align(Alignment.CenterStart),
                        fontWeight = FontWeight.Bold,
                        color = White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = message.text,
                        Modifier.align(Alignment.CenterStart),
                        fontSize = 13.sp,
                        color = White
                    )
                }
            }
        }


    }
}

@Composable
fun OtherPeopleChatItem(textOfAvatar:String, message: Message) {
    val widthOfScreen = LocalConfiguration.current.screenWidthDp.dp

    ConstraintLayout(Modifier.fillMaxWidth().padding(4.dp)) {
        val (avatarRef, spacerRef, messageRef) = createRefs()
        Box(
            Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Green)
                .constrainAs(avatarRef) {
                    start.linkTo(parent.start)
                    end.linkTo(spacerRef.start)
                    bottom.linkTo(messageRef.bottom)
                },
        ){
            Text(
                text = textOfAvatar.uppercase(Locale.getDefault()),Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .width(4.dp)
                .constrainAs(spacerRef) {
                    start.linkTo(avatarRef.end)
                    end.linkTo(messageRef.start)
                },
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(messageRef) {
                    start.linkTo(spacerRef.end)
                },
        ) {
            Column(
                Modifier
                    .align(Alignment.CenterStart)
                    .width(widthOfScreen / 2)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Green)
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            ) {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = message.username,
                        Modifier.align(Alignment.CenterStart),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = message.text,
                        Modifier.align(Alignment.CenterStart),
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ChatItemTest() {
    UserChatItem(
        textOfAvatar = "amirreza lotfi",
        message = Message(
            text,
            "amirreza lotfi",
            LocalDateTime.now().toString()
        ),
    )
}