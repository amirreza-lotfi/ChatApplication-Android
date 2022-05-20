package com.amirreza.chatapp.feature_chat.presentation.login

sealed class LoginUiEvent{
    object JoinAcceptable: LoginUiEvent()
    object JoinIllegal: LoginUiEvent()
}
