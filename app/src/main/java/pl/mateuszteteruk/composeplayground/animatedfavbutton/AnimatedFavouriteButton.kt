package pl.mateuszteteruk.composeplayground.animatedfavbutton

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.TransitionSpec
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedFavouriteButton() {
    val currentState = remember { mutableStateOf(ButtonState.Pressed) }
    val primary = MaterialTheme.colors.primary
    val background = Color.White
    val transitionDefinition = transitionDefinition<ButtonState> {
        state(name = ButtonState.Idle) {
            this[width] = 300.dp
            this[corners] = 6
            this[backgroundColor] = background
            this[textColor] = primary
            this[textOpacity] = 1f
            this[pressedHeartSize] = 24.dp
            this[idleHeartSize] = 24.dp
        }
        state(name = ButtonState.Pressed) {
            this[width] = 60.dp
            this[corners] = 48
            this[backgroundColor] = primary
            this[textColor] = background
            this[textOpacity] = 0f
            this[pressedHeartSize] = 24.dp
            this[idleHeartSize] = 24.dp
        }
        transition(from = ButtonState.Idle, to = ButtonState.Pressed) {
            textOpacity using tween(durationMillis = 1000)
            pressedHeartSize using keyframes {
                durationMillis = 2200
                24.dp at 1700
                12.dp at 1900
            }

        }
        transition(from = ButtonState.Pressed, to = ButtonState.Idle) {
            textOpacity using tween(durationMillis = 3000)
            idleHeartSize using infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 2000
                    24.dp at 1400
                    18.dp at 1500
                    24.dp at 1600
                    18.dp at 1700
                },
                repeatMode = RepeatMode.Restart
            )
        }
    }
    val transitionState = transition(
        definition = transitionDefinition,
        initState = currentState.value,
        toState = currentState.value.reverse()
    )
    val onCLick = {
        currentState.value = currentState.value.reverse()
    }
    FavouriteButton(transitionState, onCLick, currentState)
}

private fun TransitionDefinition<ButtonState>.transition(
    from: ButtonState,
    to: ButtonState,
    duration: Int = 1500,
    block: TransitionSpec<ButtonState>.() -> Unit = {}
) {
    transition(fromState = from, toState = to) {
        width using tween(durationMillis = duration)
        corners using tween(durationMillis = duration)
        backgroundColor using tween(durationMillis = duration)
        textColor using tween(durationMillis = duration)
        block()
    }
}

enum class ButtonState {
    Idle, Pressed;

    fun reverse(): ButtonState =
        when (this) {
            Idle -> Pressed
            Pressed -> Idle
        }
}