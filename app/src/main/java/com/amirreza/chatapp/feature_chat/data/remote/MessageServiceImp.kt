package com.amirreza.chatapp.feature_chat.data.remote

import com.feature_chat.data.remote.MessageJson
import com.feature_chat.data.remote.MessageService
import com.feature_chat.domain.entity.Message
import io.ktor.client.*
import io.ktor.client.request.*

class MessageServiceImp(
    private val httpClient: HttpClient
): MessageService {
    override suspend fun getAllMessages(): List<Message> {
        return try {
            httpClient.get<List<MessageJson>>(MessageService.Endpoint.GetAllMessages.url)
                .map {
                    it.toMessage()
                }
        }catch(e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }

}