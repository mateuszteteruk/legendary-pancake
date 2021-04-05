package pl.mateuszteteruk.composeplayground.animatedfavbutton

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedFavouriteButton() {
    val currentState = remember { mutableStateOf(ButtonState.Pressed) }
    val primary = MaterialTheme.colors.primary
    val background = Color.White
    val transition = updateTransition(currentState, label = "AnimatedFavouriteButton")
    val onCLick = {
        currentState.value = currentState.value.reverse()
    }
    val width = transition.animateDp(
        label = "width",
        transitionSpec = { tween(durationMillis = 1500) }
    ) { state ->
        when (state.value) {
            ButtonState.Idle -> 300.dp
            ButtonState.Pressed -> 60.dp
        }
    }
    val corners = transition.animateInt(
        label = "corners",
        transitionSpec = { tween(durationMillis = 1500) }
    ) { state ->
        when (state.value) {
            ButtonState.Idle -> 6
            ButtonState.Pressed -> 48
        }
    }
    val pressedHeartSize = transition.animateDp(
        label = "pressedHeartSize",
//        transitionSpec = {
//            keyframes {
//                if (targetState.value == ButtonState.Pressed) {
//                    durationMillis = 2200
//                    24.dp at 1700
//                    12.dp at 1900
//                }
//            }
//        }
    ) { state ->
        when (state.value) {
            ButtonState.Idle -> 24.dp
            ButtonState.Pressed -> 24.dp
        }
    }
    val idleHeartSize = transition.animateDp(
        label = "idleHeartSize",
//        transitionSpec = {
//            if (targetState.value == ButtonState.Idle) {
//                repeatable(
//                    animation = keyframes {
//                        durationMillis = 2000
//                        24.dp at 1400
//                        18.dp at 1500
//                        24.dp at 1600
//                        18.dp at 1700
//                    },
//                    repeatMode = RepeatMode.Restart,
//                    iterations = 5
//                )
//            } else {
//                keyframes { }
//            }
//        }
    ) { state ->
        when (state.value) {
            ButtonState.Idle -> 24.dp
            ButtonState.Pressed -> 24.dp
        }
    }
    val backgroundColor = transition.animateColor(
        label = "backgroundColor",
        transitionSpec = { tween(durationMillis = 1500) }
    ) { state ->
        when (state.value) {
            ButtonState.Idle -> background
            ButtonState.Pressed -> primary
        }
    }
    val textColor = transition.animateColor(
        label = "textColor",
        transitionSpec = { tween(durationMillis = 1500) }
    ) { state ->
        when (state.value) {
            ButtonState.Idle -> primary
            ButtonState.Pressed -> background
        }
    }
    val textOpacity = transition.animateFloat(
        label = "textOpacity",
        transitionSpec = {
            when (targetState.value) {
                ButtonState.Idle -> tween(durationMillis = 3000)
                ButtonState.Pressed -> tween(durationMillis = 1000)
            }
        }
    ) { state ->
        when (state.value) {
            ButtonState.Idle -> 1F
            ButtonState.Pressed -> 0F
        }
    }
    val transitionData = TransitionData(
        width = width,
        corners = corners,
        pressedHeartSize = pressedHeartSize,
        idleHeartSize = idleHeartSize,
        backgroundColor = backgroundColor,
        textColor = textColor,
        textOpacity = textOpacity
    )
    FavouriteButton(transitionData, onCLick, currentState)
}

enum class ButtonState {
    Idle, Pressed;

    fun reverse(): ButtonState =
        when (this) {
            Idle -> Pressed
            Pressed -> Idle
        }
}

class TransitionData(
    width: State<Dp>,
    corners: State<Int>,
    idleHeartSize: State<Dp>,
    pressedHeartSize: State<Dp>,
    backgroundColor: State<Color>,
    textColor: State<Color>,
    textOpacity: State<Float>,
) {

    val width by width
    val corners by corners
    val idleHeartSize by idleHeartSize
    val pressedHeartSize by pressedHeartSize
    val backgroundColor by backgroundColor
    val textColor by textColor
    val textOpacity by textOpacity
}
