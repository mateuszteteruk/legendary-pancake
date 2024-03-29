package pl.mateuszteteruk.composeplayground.modifiers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch

// source
// https://gist.github.com/KlassenKonstantin/edebb0fdc192a2b0c2d4f55b85f06abe

fun Modifier.tiltOnTouch(
    maxTiltDegrees: Float = DEF_MAX_TILT_DEGREES,
) = this.then(
    composed {
        val scope = rememberCoroutineScope()
        val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

        pointerInput(Unit) {
            forEachGesture {
                awaitPointerEventScope {
                    var newOffset = awaitFirstDown().position.normalize(size)
                    scope.launch {
                        offset.animateTo(newOffset, spring)
                    }
                    do {
                        val event = awaitPointerEvent()
                        newOffset = event.changes.last().position.normalize(size)
                        scope.launch {
                            offset.animateTo(newOffset, spring)
                        }
                    } while (event.changes.none { it.changedToUp() })
                    scope.launch {
                        offset.animateTo(Offset.Zero, releaseSpring)
                    }
                }
            }
        }.tilt(
            offset = offset.value,
            maxTiltDegrees = maxTiltDegrees
        )
    }
)

private val spring = spring<Offset>(stiffness = Spring.StiffnessMediumLow)
private val releaseSpring = spring<Offset>(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = 300f)

private fun Offset.normalize(size: IntSize) = Offset(
    ((x - (size.width / 2)) / (size.width / 2)).coerceIn(-1f..1f),
    ((y - (size.height / 2)) / (size.height / 2)).coerceIn(-1f..1f)
)

fun Modifier.tilt(
    offset: Offset = Offset.Zero,
    maxTiltDegrees: Float = DEF_MAX_TILT_DEGREES,
) = this.graphicsLayer(
    rotationY = offset.x * maxTiltDegrees,
    rotationX = -offset.y * maxTiltDegrees,
    cameraDistance = 20f
)

const val DEF_MAX_TILT_DEGREES = 15f
