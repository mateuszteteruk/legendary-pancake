package pl.mateuszteteruk.composeplayground.animatedfavbutton

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
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButton(
    transitionData: TransitionData,
    onCLick: () -> Unit,
    currentButtonState: State<ButtonState>
) {
    Button(
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = transitionData.backgroundColor),
        shape = RoundedCornerShape(transitionData.corners),
        modifier = Modifier.size(transitionData.width, 60.dp),
        onClick = {
            onCLick()
        }
    ) {
        FavouriteTextWithIcon(transitionData, currentButtonState)
    }
}

@Composable
fun FavouriteTextWithIcon(transitionData: TransitionData, currentButtonState: State<ButtonState>) {
    val (icon, iconTransition) = when (currentButtonState.value) {
        ButtonState.Idle -> Icons.Default.Favorite to transitionData.pressedHeartSize
        ButtonState.Pressed -> Icons.Default.FavoriteBorder to transitionData.idleHeartSize
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.width(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = "Icon",
                modifier = Modifier.size(iconTransition),
                tint = transitionData.textColor
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            "FAVOURITE",
            softWrap = false,
            color = transitionData.textColor,
            modifier = Modifier.alpha(transitionData.textOpacity)
        )
    }
}