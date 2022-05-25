package com.amirreza.chatapp.feature_chat.di

import android.app.Application
import com.amirreza.chatapp.feature_chat.presentation.chatroom.ChatViewModel
import com.amirreza.chatapp.feature_chat.data.remote.MessageService
import com.amirreza.chatapp.feature_chat.data.remote.MessageServiceImp
import com.amirreza.chatapp.feature_chat.data.websocket.WebSocketService
import com.feature_chat.data.websocket.WebSocketServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ChatApp:Application(){
    override fun onCreate() {
        super.onCreate()

        val dependencyModules = module{
            single{
                HttpClient(CIO){
                    install(Logging)
                    install(WebSockets)
                    install(JsonFeature){
                        serializer = KotlinxSerializer()
                    }
                }
            }

            single<MessageService> {
                MessageServiceImp(get())
            }

            single<WebSocketService> {
                WebSocketServiceImpl(get())
            }
        }

        val viewModelModule = module {
            viewModel {
                ChatViewModel(get(), get())
            }
        }

        startKoin {
            androidContext(this@ChatApp)
            modules(listOf(dependencyModules,viewModelModule))
        }
    }

}