package com.feature_chat.data.websocket

import com.amirreza.chatapp.feature_chat.data.websocket.WebSocketService
import com.feature_chat.acknowledge.Acknowledge
import com.feature_chat.data.remote.MessageJson
import com.feature_chat.domain.entity.Message
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WebSocketServiceImpl(
    private val httpClient: HttpClient
): WebSocketService {

    var socket:WebSocketSession? = null

    override suspend fun initSession(username: String): Acknowledge<Unit> {
        return try {
            socket = httpClient.webSocketSession {
                url("${WebSocketService.Endpoint.ChatSocket.url}?username=$username")
            }
            if (socket?.isActive == true){
                Acknowledge.Success(Unit)
            }else Acknowledge.Error("Couldn't established connection.",null)

        }catch (e:Exception){
            Acknowledge.Error(e.localizedMessage?:"Unknown Error",null)
        }
    }


    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        }catch (e:Exception){
            //Todo
        }
    }

    override suspend fun observeToNewMessage(): Flow<Message> {
        return try {
            socket
                ?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map{ receivedText ->
                    val json = (receivedText as? Frame.Text)?.readText() ?: ""
                    val messageJson:MessageJson = Json.decodeFromString(json)
                    messageJson.toMessage()
                }?: flow{}
        }catch (e:Exception){
            flow{}
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }

}