package pl.mateuszteteruk.composeplayground.animatedfavbutton

import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.TransitionSpec
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
    val currentState = remember { mutableStateOf(State.Pressed) }
    val primary = MaterialTheme.colors.primary
    val background = Color.White
    val transitionDefinition = transitionDefinition<State> {
        state(name = State.Idle) {
            this[width] = 300.dp
            this[corners] = 6
            this[backgroundColor] = background
            this[textColor] = primary
            this[textOpacity] = 1f
        }
        state(name = State.Pressed) {
            this[width] = 60.dp
            this[corners] = 48
            this[backgroundColor] = primary
            this[textColor] = background
            this[textOpacity] = 0f
        }
        transition(from = State.Idle, to = State.Pressed) {
            textOpacity using tween(durationMillis = 1000)
        }
        transition(from = State.Pressed, to = State.Idle) {
            textOpacity using tween(durationMillis = 3000)
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

private fun TransitionDefinition<State>.transition(
    from: State,
    to: State,
    duration: Int = 1500,
    block: TransitionSpec<State>.() -> Unit = {}
) {
    transition(fromState = from, toState = to) {
        width using tween(durationMillis = duration)
        corners using tween(durationMillis = duration)
        backgroundColor using tween(durationMillis = duration)
        textColor using tween(durationMillis = duration)
        block()
    }
}

enum class State {
    Idle, Pressed;

    fun reverse(): State =
        when (this) {
            Idle -> Pressed
            Pressed -> Idle
        }
}