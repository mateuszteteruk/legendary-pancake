package pl.mateuszteteruk.composeplayground.animatedfavbutton

import androidx.compose.animation.core.TransitionDefinition
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
        }
        state(name = State.Pressed) {
            this[width] = 60.dp
            this[corners] = 48
            this[backgroundColor] = primary
            this[textColor] = background
        }
        transition(from = State.Idle, to = State.Pressed)
        transition(from = State.Pressed, to = State.Idle)
    }
    val transitionState = transition(
        definition = transitionDefinition,
        initState = currentState.value,
        toState = currentState.value.reverse()
    )
    val onCLick = {
        currentState.value = currentState.value.reverse()
    }
    FavouriteButton(transitionState, onCLick)
}

private fun TransitionDefinition<State>.transition(
    from: State,
    to: State,
    duration: Int = 1500
) {
    transition(fromState = from, toState = to) {
        width using tween(durationMillis = duration)
        corners using tween(durationMillis = duration)
        backgroundColor using tween(durationMillis = duration)
        textColor using tween(durationMillis = duration)
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