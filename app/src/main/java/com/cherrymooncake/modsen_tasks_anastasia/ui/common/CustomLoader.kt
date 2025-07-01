package com.cherrymooncake.modsen_tasks_anastasia.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme

@Composable
fun CustomLoader(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loader_transition")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        ),
        label = "loader_angle"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_loader),
            contentDescription = "Loading...",
            modifier = Modifier
                .size(64.dp)
                .rotate(angle)
        )
    }
}

@Preview()
@Composable
private fun CustomLoaderPreview() {
    ModsenTasksAnastasiaTheme {
        CustomLoader()
    }
}