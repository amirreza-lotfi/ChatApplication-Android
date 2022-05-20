package com.amirreza.chatapp.feature_chat.presentation.chatroom

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.*
import com.amirreza.chatapp.feature_chat.presentation.chatroom.components.ChatScreenEvents
import com.feature_chat.acknowledge.Acknowledge
import com.feature_chat.data.remote.MessageService
import com.amirreza.chatapp.feature_chat.data.websocket.WebSocketService
import com.feature_chat.domain.entity.Message
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ChatViewModel(
    private val messageService: MessageService,
    private val socketService: WebSocketService,
):ViewModel(){

    private val _messageList = SnapshotStateList<Message>()
    val messageList = _messageList

    private val _textFieldValue = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    private val _username = MutableLiveData("")
    val username:LiveData<String> = _username


    init{
        getMessageFromDatabase()
        Log.i("","")
    }
    fun textValueChanged(value:String){
        _textFieldValue.value = value
    }

    fun chatScreenEvents(event:ChatScreenEvents){
        when(event){
            is ChatScreenEvents.SendMessage -> {
                sendMessage()
                _textFieldValue.value = ""
            }
            is ChatScreenEvents.SetUsername ->{
                _username.value = event.u
            }
            is ChatScreenEvents.InitChatWebSocket->{
                initWebSocket()
            }
            is ChatScreenEvents.DisconnectUser -> {
                disconnectUser()
            }
        }
    }
    private fun getMessageFromDatabase(){
        viewModelScope.launch {
            _messageList.addAll(messageService.getAllMessages())
        }
    }
    private fun sendMessage(){
        viewModelScope.launch {
            if(_textFieldValue.value.isNotBlank())
                socketService.sendMessage(_textFieldValue.value)
        }
    }

    private fun initWebSocket(){
        viewModelScope.launch {
            when(socketService.initSession(_username.value!!)){
                is Acknowledge.Success -> {
                    observeToNewMessage()
                }
                is Acknowledge.Error -> {
                    _toastEvent.emit("Failed at connecting. Please try again.")
                }
            }
        }
    }
    private fun disconnectUser(){
        viewModelScope.launch {
            socketService.closeSession()
        }
    }
    private suspend fun observeToNewMessage(){
        socketService.observeToNewMessage()
            .collect { message ->
                _messageList.add(0,message)
            }
    }
    override fun onCleared() {
        super.onCleared()
        disconnectUser()
    }
}