package com.example.myapplication.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.LoginResult

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: (String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is LoginResult.Loading -> CircularProgressIndicator()
            is LoginResult.Error -> Text(
                text = (state as LoginResult.Error).message,
                color = MaterialTheme.colorScheme.error
            )
            is LoginResult.Success -> {
                LaunchedEffect(Unit) {
                    onLoginSuccess((state as LoginResult.Success).user)
                }
            }
            else -> {}
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.login(username, password) },
            enabled = state !is LoginResult.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}
