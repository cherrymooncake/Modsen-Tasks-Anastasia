package com.cherrymooncake.modsen_tasks_anastasia.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.LoginUseCase
import com.cherrymooncake.modsen_tasks_anastasia.ui.utils.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _event = SingleFlowEvent<LoginEvent>(viewModelScope)
    val event = _event.flow

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnLoginChanged -> {
                _state.update { it.copy(loginInput = intent.value) }
            }
            is LoginIntent.OnPasswordChanged -> {
                _state.update { it.copy(passwordInput = intent.value) }
            }
            LoginIntent.OnLoginClick -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = loginUseCase(
                login = _state.value.loginInput,
                pass = _state.value.passwordInput
            )

            _state.update { it.copy(isLoading = false) }

            if (result.isSuccess) {
                _event.emit(LoginEvent.NavigateToSuccessScreen)
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                _event.emit(LoginEvent.ShowError(errorMessage))
            }
        }
    }
}