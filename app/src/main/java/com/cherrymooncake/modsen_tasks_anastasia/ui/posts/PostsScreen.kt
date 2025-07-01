package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrymooncake.modsen_tasks_anastasia.ui.common.CustomLoader
import com.cherrymooncake.modsen_tasks_anastasia.ui.common.PostItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme


@Composable
fun PostsScreen(
    viewModel: PostsViewModel
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    PostsContent(state = state)

}

@Composable
private fun PostsContent(state: PostsState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CustomLoader(modifier = Modifier.size(80.dp))
            }
            state.error != null -> {
                Text(
                    text = stringResource(state.error),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
            else -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(R.string.posts_header),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = state.posts, key = { it.id }) { post ->
                            PostItem(post = post)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Posts Screen - Loading")
@Composable
private fun PostsContentPreviewLoading() {
    ModsenTasksAnastasiaTheme {
        PostsContent(state = PostsState(isLoading = true))
    }
}