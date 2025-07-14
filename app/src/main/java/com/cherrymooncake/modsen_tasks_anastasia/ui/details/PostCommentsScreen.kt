package com.cherrymooncake.modsen_tasks_anastasia.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.common.CustomLoader
import com.cherrymooncake.modsen_tasks_anastasia.ui.common.PostItem
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.toUiModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PostCommentsScreen(post: PostDomainModel) {
    val viewModel: PostCommentsViewModel = koinViewModel(
        parameters = { parametersOf(post) }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onIntent = remember<(PostCommentsIntent) -> Unit> { { viewModel.onIntent(it) } }

    PostCommentsContent(
        state = state,
        intent = onIntent
    )
}

@Composable
private fun PostCommentsContent(
    state: PostCommentsState,
    intent: (PostCommentsIntent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.post_header),
                style = MaterialTheme.typography.headlineSmall,
            )
            PostItem(
                post = state.post.toUiModel(),
                onFavoriteClick = { intent(PostCommentsIntent.ToggleFavorite) }
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))
            Text(stringResource(R.string.comments_header), style = MaterialTheme.typography.titleLarge)
        }
        if (state.isLoading) {
            item {
                Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                    CustomLoader()
                }
            }
        } else {
            items(state.comments, key = { it.id }) { comment ->
                CommentItem(comment = comment)
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
private fun CommentItem(comment: CommentUiModel) {
    Column {
        Text(
            text = comment.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(text = comment.body, style = MaterialTheme.typography.bodyMedium)
    }
}