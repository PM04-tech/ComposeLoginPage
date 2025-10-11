package com.example.composeloginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeloginpage.common.CustomTextField
import com.example.composeloginpage.ui.theme.ComposeLoginPageTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeloginpage.viewModel.LoginViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}





@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text("Login", style = MaterialTheme.typography.displaySmall)

        Spacer(modifier = Modifier.height(20.dp))

        // Email Field
        CustomTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = "Email",
            isError = state.email.isNotEmpty() && !state.isEmailValid,
            errorMessage = "Invalid email format"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password Field
        CustomTextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = "Password",
            isPassword = true,
            isError = state.password.isNotEmpty() && !state.isPasswordValid,
            errorMessage = "Password must be at least 6 characters"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
              viewModel.onLoginClick()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text(if(state.isLoading) "Logging in..." else "Login")
        }

        state.message?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = it,
                color = if(state.isLoginSuccessful)
                        MaterialTheme.colorScheme.primary
                       else
                        MaterialTheme.colorScheme.error
                      )

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLoginPageTheme {
        LoginScreen()
    }
}