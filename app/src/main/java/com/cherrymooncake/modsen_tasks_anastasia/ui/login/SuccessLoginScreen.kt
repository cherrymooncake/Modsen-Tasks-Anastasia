package com.cherrymooncake.modsen_tasks_anastasia.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cherrymooncake.modsen_tasks_anastasia.R

@Composable
fun SuccessLoginScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.success_screen_text), style = MaterialTheme.typography.headlineMedium)
    }
}