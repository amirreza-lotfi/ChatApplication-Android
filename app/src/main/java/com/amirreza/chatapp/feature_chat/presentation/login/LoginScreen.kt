package com.amirreza.chatapp.feature_chat.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amirreza.chatapp.feature_chat.presentation.login.LoginUiEvent
import com.amirreza.chatapp.feature_chat.presentation.login.LoginViewModel
import com.amirreza.chatapp.feature_chat.presentation.login.components.LoginButton
import com.amirreza.chatapp.feature_chat.presentation.login.components.LoginScreenImage
import com.amirreza.chatapp.feature_chat.presentation.login.components.LoginTextFiled
import com.amirreza.chatapp.ui.theme.Blue
import com.amirreza.chatapp.ui.theme.Green
import com.amirreza.chatapp.ui.theme.White
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    onNavigateToChatRoom:(String)->Unit
) {

    Box(
        Modifier
            .fillMaxSize()
            .background(Blue)
            .padding(20.dp)
    ){
        Column {
            Spacer(modifier = Modifier.height(50.dp))
            LoginScreenImage()
            Spacer(modifier = Modifier.height(56.dp))
            Text(
                "Welcome Back!",
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,

            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                "Please log into chatroom",
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = White,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(24.dp))

            LoginTextFiled(
                value = loginViewModel.username.value,
                placeHolder = {Text("Your First Name")},
                onValueChange = { loginViewModel.usernameChanged(it) },
            )

            Spacer(modifier = Modifier.height(16.dp))

            LoginTextFiled(
                value =  loginViewModel.lastname.value,
                placeHolder = {Text("Your Last Name")},
                onValueChange = { loginViewModel.lastnameChanged(it) },
            )

            Spacer(modifier = Modifier.height(24.dp))

            LoginButton(
                onClick = {
                    loginViewModel.joinButtonClicked()
                }
            )

        }
        
    }
    LaunchedEffect(key1 = 1){
        loginViewModel.uiEvent.collect {
            when(it){
                is LoginUiEvent.JoinAcceptable -> {
                    onNavigateToChatRoom(loginViewModel.fullName())
                }
                is LoginUiEvent.JoinIllegal -> {
                    //todo show toast
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    LoginScreen(LoginViewModel()) {}
}