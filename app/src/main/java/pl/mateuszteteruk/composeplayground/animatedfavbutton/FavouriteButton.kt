package pl.mateuszteteruk.composeplayground.animatedfavbutton

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButton(transitionState: TransitionState, onCLick: () -> Unit, currentState: MutableState<State>) {
    Button(
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = transitionState[backgroundColor]),
        shape = RoundedCornerShape(transitionState[corners]),
        modifier = Modifier.size(transitionState[width], 60.dp),
        onClick = {
            onCLick()
        }
    ) {
        FavouriteTextWithIcon(transitionState, currentState)
    }
}

@Composable
fun FavouriteTextWithIcon(transitionState: TransitionState, currentState: MutableState<State>) {
    val icon = when (currentState.value) {
        State.Idle -> Icons.Default.Favorite
        State.Pressed -> Icons.Default.FavoriteBorder
    }
    val iconTransition = when (currentState.value) {
        State.Idle -> transitionState[pressedHeartSize]
        State.Pressed -> transitionState[idleHeartSize]
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.width(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                tint = transitionState[textColor],
                imageVector = icon,
                modifier = Modifier.size(iconTransition)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            "FAVOURITE",
            softWrap = false,
            color = transitionState[textColor],
            modifier = Modifier.alpha(transitionState[textOpacity])
        )
    }
}