package com.example.composeloginpage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class LoginEvent {
    object LoginSuccess : LoginEvent()
    data class LoginError(val message: String) : LoginEvent()
}

class LoginViewModel: ViewModel() {
    //Use StateFlow for state that UI always cares about.
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

   // Use SharedFlow for events that should happen only once.
    // SharedFlow → one-time events (messages, navigation)
    private val _eventFlow = MutableSharedFlow<LoginEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(
            email = newEmail,
            isEmailValid = isValidEmail(newEmail)
        )
    }


    fun onPasswordChange(newPassword: String){
        val isPasswordValid = newPassword.length >= 6
        _uiState.value = _uiState.value.copy(password = newPassword, isPasswordValid = isPasswordValid)

    }

    fun onLoginClick(){
        val currentUiState = _uiState.value
        if(!currentUiState.isEmailValid || !currentUiState.isPasswordValid){
            viewModelScope.launch {
                _eventFlow.emit(LoginEvent.LoginError("Invalid email or password"))
            }
            return
        }

        viewModelScope.launch {
            _uiState.value = currentUiState.copy(isLoading = true, message = null)
            delay(1000)
            if (currentUiState.email == "test@example.com" && currentUiState.password == "123456") {
                _uiState.value = currentUiState.copy(
                    isLoading = false,
                    isLoginSuccessful = true,
                    message = "Login Successful"
                )

            }else {
                _uiState.value = currentUiState.copy(
                    isLoading = false,
                    isLoginSuccessful = false,
                    message = "Invalid credentials"

                )
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return Regex(emailRegex).matches(email)
    }



}