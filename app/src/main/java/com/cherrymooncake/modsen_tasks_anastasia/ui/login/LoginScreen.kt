package com.cherrymooncake.modsen_tasks_anastasia.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.ui.navigation.ScreenRoute
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onNavigateToSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onIntent: (LoginIntent) -> Unit by remember { mutableStateOf(viewModel::onIntent) }
    val event: Flow<LoginEvent> by remember { mutableStateOf(viewModel.event) }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        event.collect { event ->
            when (event) {
                is LoginEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is LoginEvent.NavigateToSuccessScreen -> {
                    onNavigateToSuccess()
                }
            }
        }
    }
    LoginScreenContent(
        state = state,
        onIntent = onIntent
    )
}

@Composable
private fun LoginScreenContent(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.login_screen_header),
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(
                value = state.loginInput,
                onValueChange = { onIntent(LoginIntent.OnLoginChanged(it)) },
                label = { Text(stringResource(R.string.login_screen_login_field_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.passwordInput,
                onValueChange = { onIntent(LoginIntent.OnPasswordChanged(it)) },
                label = { Text(stringResource(R.string.login_screen_password_field_label)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { onIntent(LoginIntent.OnLoginClick) },
                enabled = state.isLoginButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.login_screen_button_text))
            }
        }
    }
}

@Preview(showBackground = true, name = "Login Screen - Default")
@Composable
private fun LoginScreenPreview() {
    ModsenTasksAnastasiaTheme {
        LoginScreenContent(
            state = LoginState(loginInput = "user", passwordInput = "1234"),
            onIntent = {}
        )
    }
}

//@Composable
//fun LoginScreen(navController: NavController, viewModel: LoginViewModel = koinViewModel()) {
//    val state by viewModel.state.collectAsStateWithLifecycle()
//    val context = LocalContext.current
//    LaunchedEffect(Unit) {
//        viewModel.event.collect { event ->
//            when (event) {
//                is LoginEvent.ShowError -> {
//                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
//                }
//                is LoginEvent.NavigateToSuccessScreen -> { //
//                    navController.navigate(ScreenRoute.SuccessLogin.route) {
//                        popUpTo(ScreenRoute.Login.route) { inclusive = true }
//                    }
//                }
//            }
//        }
//    }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(32.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Text( text = stringResource(R.string.login_screen_header), style = MaterialTheme.typography.headlineMedium)
//
//            OutlinedTextField(
//                value = state.loginInput,
//                onValueChange = { viewModel.onIntent(LoginIntent.OnLoginChanged(it)) },
//                label = { Text(stringResource(R.string.login_screen_login_field_label)) },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            OutlinedTextField(
//                value = state.passwordInput,
//                onValueChange = { viewModel.onIntent(LoginIntent.OnPasswordChanged(it)) },
//                label = { Text(stringResource(R.string.login_screen_password_field_label)) },
//                visualTransformation = PasswordVisualTransformation(),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Button(
//                onClick = { viewModel.onIntent(LoginIntent.OnLoginClick) },
//                enabled = state.isLoginButtonEnabled,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(stringResource(R.string.login_screen_button_text))
//            }
//        }
//    }
//
//}