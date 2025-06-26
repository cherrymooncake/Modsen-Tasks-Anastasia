package com.cherrymooncake.modsen_tasks_anastasia.ui.login

data class LoginState(
    val loginInput: String = "",
    val passwordInput: String = "",
    val isLoading: Boolean = false,
) {
    val isLoginButtonEnabled: Boolean
        get() = loginInput.isNotBlank() && passwordInput.isNotBlank() && !isLoading
}

sealed interface LoginIntent {
    data class OnLoginChanged(val value: String) : LoginIntent
    data class OnPasswordChanged(val value: String) : LoginIntent
    data object OnLoginClick : LoginIntent
}

sealed interface LoginEvent {
    data class ShowError(val message: String) : LoginEvent
    data object NavigateToSuccessScreen : LoginEvent
}
