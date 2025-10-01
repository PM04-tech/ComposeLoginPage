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
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text("Login", style = MaterialTheme.typography.displayMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Email Field
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            isError = email.isNotEmpty() && !isEmailValid,
            errorMessage = "Invalid email format"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password Field
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            isError = password.isNotEmpty() && !isPasswordValid,
            errorMessage = "Password must be at least 6 characters"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                message = if (isEmailValid && isPasswordValid) {
                    "Login Successful ✅"
                } else {
                    "Please fix errors before login"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isEmailValid && isPasswordValid
        ) {
            Text("Login")
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                message,
                color = if (message.contains("Successful")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLoginPageTheme {
        LoginScreen()
    }
}