package com.amirreza.chatapp.feature_chat.data.remote

import androidx.annotation.Keep
import com.feature_chat.domain.entity.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*


data class MessageJson(
    val text:String,
    val username:String,
    val dateCreated:Long,
    val id:String
){
    fun toMessage():Message{
        val formattedDate = DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(Date(dateCreated))

        return Message(
            text,username,formattedDate
        )
    }
}