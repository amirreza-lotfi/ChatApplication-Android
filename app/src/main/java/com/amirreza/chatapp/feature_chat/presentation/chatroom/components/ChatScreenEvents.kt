package com.amirreza.chatapp.feature_chat.presentation.chatroom.components

sealed class ChatScreenEvents {
    object SendMessage: ChatScreenEvents()
    data class SetUsername(val u:String): ChatScreenEvents()
    object InitChatWebSocket:ChatScreenEvents()
    object DisconnectUser:ChatScreenEvents()
}
