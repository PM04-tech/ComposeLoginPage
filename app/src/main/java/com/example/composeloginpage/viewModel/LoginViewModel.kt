package com.example.composeloginpage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

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
            _uiState.value = currentUiState.copy(message = "Please fix errors before login")
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