package com.amirreza.chatapp.feature_chat.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.amirreza.chatapp.ui.theme.White

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginTextFiled(
    value:String,
    placeHolder:@Composable ()-> Unit,
    onValueChange:(String)->Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        placeholder = { placeHolder() },
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = White,
            placeholderColor = White,
            focusedIndicatorColor = White,
            unfocusedIndicatorColor = White,
            disabledIndicatorColor = White
        ),
        modifier = Modifier.fillMaxWidth(),
        keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()}),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}