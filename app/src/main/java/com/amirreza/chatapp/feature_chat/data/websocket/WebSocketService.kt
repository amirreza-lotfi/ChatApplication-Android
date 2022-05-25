package com.amirreza.chatapp.feature_chat.data.websocket

import com.feature_chat.acknowledge.Acknowledge
import com.feature_chat.domain.entity.Message
import kotlinx.coroutines.flow.Flow

interface WebSocketService {
    suspend fun sendMessage(message: String)
    suspend fun observeToNewMessage(): Flow<Message>
    suspend fun initSession(username:String): Acknowledge<Unit>
    suspend fun closeSession()

    companion object{
        //todo -> write your server address here
        const val BASE_URL = "ws://192.168.0.142:8080/"
    }
    sealed class Endpoint(val url:String){
        object ChatSocket: Endpoint("$BASE_URL/chatroom-websockets")
    }
}