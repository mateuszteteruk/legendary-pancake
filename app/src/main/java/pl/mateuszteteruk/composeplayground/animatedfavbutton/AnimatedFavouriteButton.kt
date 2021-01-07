package pl.mateuszteteruk.composeplayground.animatedfavbutton

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
        }
        state(name = State.Pressed) {
            this[width] = 60.dp
        }
        transition(fromState = State.Idle, toState = State.Pressed) {
            width using tween(durationMillis = 1500)
        }
        transition(fromState = State.Pressed, toState = State.Idle) {
            width using tween(durationMillis = 1500)
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
    FavouriteButton(transitionState, onCLick)
}

enum class State {
    Idle, Pressed;

    fun reverse(): State =
        when (this) {
            Idle -> Pressed
            Pressed -> Idle
        }
}