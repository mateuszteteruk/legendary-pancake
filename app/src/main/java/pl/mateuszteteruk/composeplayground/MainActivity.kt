package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.mateuszteteruk.composeplayground.modifiers.tiltOnTouch
import pl.mateuszteteruk.composeplayground.ui.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Card(
                            modifier = Modifier
                                .size(width = 300.dp, height = 400.dp)
                                .tiltOnTouch(),
                            shape = RoundedCornerShape(24.dp),
                            border = BorderStroke(2.dp, Color.Black)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.smoothie),
                                contentScale = ContentScale.FillWidth,
                                contentDescription = "",
                            )
                        }
                    }
                }
            }
        }
    }
}
