package com.example.composeloginpage.viewModel

data class LoginUiState (
    var email: String = "",
    var password: String = "",
    val isLoading: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val message: String? = null,
    val isLoginSuccessful: Boolean = false
) {

}