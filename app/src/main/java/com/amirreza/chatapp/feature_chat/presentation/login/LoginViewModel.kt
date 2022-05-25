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

    private val _lastname = mutableStateOf("")
    val lastname:State<String> = _lastname

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun fullName():String{
        return _username.value + " " + _lastname.value
    }
    fun usernameChanged(value:String){
        _username.value = value
    }
    fun lastnameChanged(value:String){
        _lastname.value = value
    }

    fun joinButtonClicked(){
        viewModelScope.launch {
            if (username.value.isNotBlank() && lastname.value.isNotBlank()) {
                _uiEvent.emit(LoginUiEvent.JoinAcceptable)
            }else{
                _uiEvent.emit(LoginUiEvent.JoinIllegal)
            }
        }
    }
}