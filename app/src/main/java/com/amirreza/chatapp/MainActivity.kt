package com.amirreza.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amirreza.chatapp.feature_chat.presentation.chatroom.ChatroomScreen
import com.amirreza.chatapp.ui.theme.ChatAppTheme
import com.amirreza.chatapp.feature_chat.presentation.chatroom.ChatViewModel
import com.amirreza.chatroom_application.feature_chat.presentation.login.LoginScreen
import org.koin.androidx.viewmodel.ext.android.getViewModel

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = LOGIN_SCREEN_ROUT){
                    composable(LOGIN_SCREEN_ROUT){
                        LoginScreen{ username ->
                            navController.navigate("$CHAT_SCREEN_ROUT/$username")
                        }
                    }
                    composable(
                        route = "$CHAT_SCREEN_ROUT/{username}",
                        arguments = listOf(
                            navArgument("username"){
                                type = NavType.StringType
                            }
                        )
                    ){
                        val chatViewModel = getViewModel<ChatViewModel>()
                        val username = it.arguments?.get("username").toString()
                        ChatroomScreen(chatViewModel,username = username)
                    }
                }
            }
        }
    }
    companion object{
        const val LOGIN_SCREEN_ROUT = "login"
        const val CHAT_SCREEN_ROUT = "chat"
    }
}
