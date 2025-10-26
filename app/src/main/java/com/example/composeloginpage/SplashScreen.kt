package com.example.composeloginpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }

    }

    Image(
        painter = painterResource(id = R.drawable.superhero),
        contentDescription = "Splash Background",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
}