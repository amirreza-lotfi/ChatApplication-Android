package com.amirreza.chatapp.feature_chat.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel(){

    private val _username = mutableStateOf("")
    val username:State<String> = _username

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun usernameChanged(value:String){
        _username.value = value
    }

    fun joinButtonClicked(){
        viewModelScope.launch {
            if (username.value.isNotBlank()) {
                _uiEvent.emit(LoginUiEvent.JoinAcceptable)
            }else{
                _uiEvent.emit(LoginUiEvent.JoinIllegal)
            }
        }
    }
}