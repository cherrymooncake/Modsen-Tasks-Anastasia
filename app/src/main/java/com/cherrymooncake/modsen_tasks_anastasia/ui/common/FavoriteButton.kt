package com.cherrymooncake.modsen_tasks_anastasia.ui.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "favorite_infinite_transition")

    val pulsatingSize: Dp by if (isFavorite) {
        infiniteTransition.animateValue(
            initialValue = 26.dp,
            targetValue = 30.dp,
            typeConverter = Dp.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulsating_size"
        )
    } else {
        remember { mutableStateOf(24.dp) }
    }

    val shimmeringColor by if (isFavorite) {
        infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Magenta,
            animationSpec = infiniteRepeatable(
                animation = tween(1500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "shimmer_color"
        )
    } else {
        remember { mutableStateOf(Color.Gray) }
    }

    Icon(
        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
        contentDescription = "Favorite",
        modifier = modifier
            .size(pulsatingSize)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        tint = shimmeringColor
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    ModsenTasksAnastasiaTheme {
        Row(modifier = Modifier.padding(32.dp), horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            FavoriteButton(isFavorite = true, onClick = {})
            FavoriteButton(isFavorite = false, onClick = {})
        }
    }
}