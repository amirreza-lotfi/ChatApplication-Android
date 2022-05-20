package com.amirreza.chatapp.feature_chat.presentation.chatroom

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amirreza.chatapp.feature_chat.presentation.chatroom.components.BottomTextFiled
import com.amirreza.chatapp.feature_chat.presentation.chatroom.components.ChatList
import com.amirreza.chatapp.feature_chat.presentation.chatroom.components.ChatScreenEvents
import kotlinx.coroutines.flow.collect


@ExperimentalComposeUiApi
@Composable
fun ChatroomScreen(
    chatViewModel: ChatViewModel,
    username:String
) {
    chatViewModel.chatScreenEvents(ChatScreenEvents.SetUsername(username))

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = true){
        chatViewModel.toastEvent.collect { text ->
            Toast.makeText(context, text, Toast.LENGTH_SHORT)
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


    Box(Modifier.fillMaxSize()) {
        ConstraintLayout(
            Modifier.fillMaxSize()
        ) {
            val (chatRef,textFiledRef) = createRefs()

            ChatList(
                Modifier
                    .constrainAs(ConstrainedLayoutReference(chatRef)){
                        top.linkTo(parent.top)
                        bottom.linkTo(textFiledRef.top)
                        start.linkTo(parent.start)
                    },
                chatViewModel.messageList,
                chatViewModel.username.value!!
            )
            BottomTextFiled(
                modifier = Modifier
                    .constrainAs(ConstrainedLayoutReference(textFiledRef)) {
                        start.linkTo(parent.start)
                        top.linkTo(chatRef.top)
                        bottom.linkTo(parent.bottom)
                    },
                value = chatViewModel.textFieldValue.value,
                onValueChange = { chatViewModel.textValueChanged(it)},
                onSendClicked = {
                    chatViewModel.chatScreenEvents(ChatScreenEvents.SendMessage)
                }
            )
        }
    }
}
