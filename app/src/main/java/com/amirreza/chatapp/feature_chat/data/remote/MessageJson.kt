package com.feature_chat.data.remote

import com.feature_chat.domain.entity.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*

@Serializable
data class MessageJson(
    val text:String,
    val username:String,
    val time:Long,
    val id:String
){
    fun toMessage():Message{

        val formattedDate = DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(Date(time))

        return Message(
            text,username,formattedDate
        )
    }
}