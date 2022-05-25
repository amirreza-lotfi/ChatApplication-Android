package com.amirreza.chatapp.feature_chat.data.remote

import com.feature_chat.domain.entity.Message

interface MessageService {
    suspend fun getAllMessages():List<Message>


    sealed class Endpoint(val url:String){
        object GetAllMessages: Endpoint("$BASE_URL/messages")
    }

    companion object{
        //todo -> write your server base url here
        const val BASE_URL = ""
    }
}