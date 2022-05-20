package com.feature_chat.acknowledge

sealed class Acknowledge<T>(val message:String?=null, val data:T?=null){
    class Success<T>(data: T?): Acknowledge<T>(null,data)
    class Error<T>(message: String, data: T?): Acknowledge<T>(message,data)
}
