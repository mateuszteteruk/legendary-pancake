package pl.mateuszteteruk.composeplayground.animatedfavbutton

import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedFavouriteButton() {
    val currentState = remember { mutableStateOf(State.Pressed) }
    val transitionDefinition = transitionDefinition<State> {
        state(name = State.Idle) {
            this[width] = 300.dp
            this[corners] = 6
        }
        state(name = State.Pressed) {
            this[width] = 60.dp
            this[corners] = 48
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