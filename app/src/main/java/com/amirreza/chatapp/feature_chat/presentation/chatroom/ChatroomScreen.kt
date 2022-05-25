package com.amirreza.chatapp.feature_chat.presentation.chatroom

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amirreza.chatapp.feature_chat.data.remote.MessageServiceImp
import com.amirreza.chatapp.feature_chat.presentation.chatroom.components.BottomTextFiled
import com.amirreza.chatapp.feature_chat.presentation.chatroom.components.ChatList
import com.amirreza.chatapp.feature_chat.presentation.chatroom.components.ChatScreenEvents
import com.amirreza.chatapp.ui.theme.Blue
import com.amirreza.chatapp.ui.theme.Green
import com.amirreza.chatapp.ui.theme.White
import com.feature_chat.data.websocket.WebSocketServiceImpl
import com.feature_chat.domain.entity.Message
import io.ktor.client.*
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime


@ExperimentalComposeUiApi
@Composable
fun ChatroomScreen(
    chatViewModel: ChatViewModel,
    username:String
) {
    chatViewModel.chatScreenEvents(ChatScreenEvents.SetUsername(username))

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = true){
        chatViewModel.toastEvent.collect { text ->
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    DisposableEffect(key1 = lifecycleOwner){
        val observer = LifecycleEventObserver { _, event ->
            if(event == Lifecycle.Event.ON_START){
                chatViewModel.chatScreenEvents(ChatScreenEvents.InitChatWebSocket)
            } else if(event == Lifecycle.Event.ON_STOP){
                chatViewModel.chatScreenEvents(ChatScreenEvents.DisconnectUser)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 16.dp,)
    ){
        Column(
            Modifier.align(Alignment.BottomCenter)
        ){
            Box(Modifier.height(screenHeight-86.dp).padding(bottom = 8.dp)){
                ChatList(
                    Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomCenter),
                    chatViewModel.messageList,
                    chatViewModel.username.value!!
                )
            }

            BottomTextFiled(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                value = chatViewModel.textFieldValue.value,
                onValueChange = {chatViewModel.textValueChanged(it)},
                onSendClicked = {
                    chatViewModel.chatScreenEvents(ChatScreenEvents.SendMessage)
                }
            )
        }
    }
}
