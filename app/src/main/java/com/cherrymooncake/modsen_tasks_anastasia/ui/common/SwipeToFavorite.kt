package com.cherrymooncake.modsen_tasks_anastasia.ui.common

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.abs

fun Modifier.swipeToToggleFavorite(onSwiped: () -> Unit): Modifier = this.pointerInput(Unit) {
    var totalDrag = 0f
    detectHorizontalDragGestures(
        onDragStart = { totalDrag = 0f },
        onDragEnd = {
            if (abs(totalDrag) > size.width * 0.3f) {
                onSwiped()
            }
        },
        onHorizontalDrag = { change, dragAmount ->
            totalDrag += dragAmount
            change.consume()
        }
    )
}