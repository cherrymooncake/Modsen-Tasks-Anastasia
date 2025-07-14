package com.cherrymooncake.modsen_tasks_anastasia.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostUiModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme

@Composable
fun PostItem(
    post: PostUiModel,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        FavoriteButton(
            isFavorite = post.isFavorite,
            onClick = onFavoriteClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostItemPreview() {
    ModsenTasksAnastasiaTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            PostItem(
                post = PostUiModel(
                    id = 1,
                    userId = 1,
                    title = "This is a favorite post",
                    body = "The body of the post that is marked as a favorite.",
                    isFavorite = true
                ),
                onFavoriteClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            PostItem(
                post = PostUiModel(
                    id = 2,
                    userId = 1,
                    title = "This is a regular post with a much longer title to see how it wraps",
                    body = "This is the body of a regular post. It's not a favorite. We can make this text longer to see how the overflow with ellipsis works.",
                    isFavorite = false
                ),
                onFavoriteClick = {}
            )
        }
    }
}

