package com.amirreza.chatroom_application.feature_chat.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amirreza.chatapp.feature_chat.presentation.login.LoginUiEvent
import com.amirreza.chatapp.feature_chat.presentation.login.LoginViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    onNavigateToChatRoom:(String)->Unit
) {

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.align(Alignment.Center)
        ) {
            OutlinedTextField(
                value = loginViewModel.username.value,
                placeholder = {Text("Enter your name...")},
                onValueChange = { loginViewModel.usernameChanged(it) },

            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    loginViewModel.joinButtonClicked()
                }
            ) {
                Text(text = "Join to Chatroom")
            }
        }

        LaunchedEffect(key1 = 1){
            loginViewModel.uiEvent.collect {
                when(it){
                    is LoginUiEvent.JoinAcceptable -> {
                        onNavigateToChatRoom(loginViewModel.username.value)
                    }
                    is LoginUiEvent.JoinIllegal -> {
                        //todo show toast
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Test() {
//    LoginScreen(LoginViewModel())
//}