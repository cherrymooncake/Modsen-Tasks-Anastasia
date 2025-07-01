package com.cherrymooncake.modsen_tasks_anastasia.ui.taskList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme

@Composable
fun TaskListScreen(
    onNavigateToTask1: () -> Unit,
    onNavigateToTask2: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(R.string.task_list_screen_header),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavigateToTask1,
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(stringResource(R.string.task_list_screen_first_task))
        }
        Button(
            onClick = onNavigateToTask2,
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(stringResource(R.string.task_list_screen_second_task))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskListScreenPreview() {
    ModsenTasksAnastasiaTheme {
        TaskListScreen(
            onNavigateToTask1 = {},
            onNavigateToTask2 = {}
        )
    }
}
