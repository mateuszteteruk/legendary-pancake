package pl.mateuszteteruk.composeplayground.animatedfavbutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButton() {
    Button(
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonConstants.defaultOutlinedButtonColors(),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.size(300.dp, 60.dp),
        onClick = {}
    ) {
        FavouriteTextWithIcon()
    }
}

@Composable
fun FavouriteTextWithIcon() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.width(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                tint = MaterialTheme.colors.primary,
                imageVector = Icons.Default.FavoriteBorder,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            "ADD TO FAVORITES!",
            softWrap = false,
            color = MaterialTheme.colors.primary
        )
    }
}