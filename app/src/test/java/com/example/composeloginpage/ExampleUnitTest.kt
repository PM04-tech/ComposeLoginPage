package com.example.composeloginpage

import com.example.composeloginpage.viewModel.LoginViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup(){
        viewModel = LoginViewModel()
    }

    @Test
    fun emailValidation(){
        viewModel.onEmailChange("test@example.com")
        assertEquals(true, viewModel.uiState.value.isEmailValid)
    }

    @Test
    fun invalidEmailValidation(){
        val invalidEmail = listOf(
            "test",
            "test@",
            "test@example",
            "test@example."
        )

        for (email in invalidEmail){
            viewModel.onEmailChange(email)
            assertEquals(false, viewModel.uiState.value.isEmailValid)
        }
    }



    @Test
    fun passwordValidation(){
        viewModel.onPasswordChange("123456")
        assertEquals(true, viewModel.uiState.value.isPasswordValid)
    }

    @Test
    fun invalidPasswordValidation(){
        viewModel.onPasswordChange("1234")
        assertEquals(false, viewModel.uiState.value.isPasswordValid)

    }

}