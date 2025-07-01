package com.cherrymooncake.modsen_tasks_anastasia.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostUiModel

@Composable
fun PostItem(
    post: PostUiModel,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outline,
            )
    ){
        Column (
            modifier = Modifier
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodyMedium,
                )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostItemPreview(){
    Column {
        PostItem(
            PostUiModel(
                1,
                1,
                "Post title 1",
                "Post body 1."
            )
        )
        PostItem(
            PostUiModel(
                2,
                2,
                "Post title 2",
                "Post body 2. Post body 2. Post body 2. Post body 2." +
                "Post body 2. Post body 2. Post body 2. Post body 2."
            )
        )
    }

}
