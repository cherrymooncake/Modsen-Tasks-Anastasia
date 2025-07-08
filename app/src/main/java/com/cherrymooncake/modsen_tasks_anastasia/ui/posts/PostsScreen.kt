package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrymooncake.modsen_tasks_anastasia.ui.common.CustomLoader
import com.cherrymooncake.modsen_tasks_anastasia.ui.common.PostItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.common.SearchTextField
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel


@Composable
fun PostsScreen(
    onPostClick: (PostDomainModel) -> Unit,
    viewModel: PostsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onIntent = remember<(PostsIntent) -> Unit> { { viewModel.sendIntent(it) } }
    val eventFlow: Flow<PostsEvent> by remember { mutableStateOf(viewModel.event) }

    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when (event) {
                is PostsEvent.NavigateToPostDetails -> {
                    onPostClick(event.post)
                }
            }
        }
    }

    PostsContent(
        state = state,
        onIntent = onIntent,
    )
}

@Composable
private fun PostsContent(
    state: PostsState,
    onIntent: (PostsIntent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField(
            value = state.searchQuery,
            onValueChange = { query -> onIntent(PostsIntent.OnSearchQueryChanged(query)) },
            modifier = Modifier.padding(16.dp)
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CustomLoader(modifier = Modifier
                        .size(80.dp)
                    )
                }

                state.error != null -> {
                    Text(
                        text = stringResource(state.error),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = stringResource(R.string.posts_header),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(items = state.filteredPosts, key = { it.id }) { post ->
                                Box(modifier = Modifier.clickable {
                                    onIntent(PostsIntent.OnPostClick(post.toDomainModel()))
                                }) {
                                    PostItem(post = post)
                                }
                                Divider(
                                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                    thickness = 1.dp
                                )
                            }
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
        PostsContent(
            state = PostsState(isLoading = true),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true, name = "Posts Screen - With Data")
@Composable
private fun PostsContentPreviewData() {
    ModsenTasksAnastasiaTheme {
        PostsContent(
            state = PostsState(
                isLoading = false,
                filteredPosts = listOf(
                    PostUiModel(
                        id = 1,
                        userId = 1,
                        title = "This is a preview title",
                        body = "This is a preview body for the post item, it can be quite long."
                    ),
                    PostUiModel(
                        id = 2,
                        userId = 1,
                        title = "Another preview title",
                        body = "Another preview body to see how multiple items look in a list."
                    )
                )
            ),
            onIntent = {},
        )
    }
}
