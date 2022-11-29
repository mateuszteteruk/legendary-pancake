package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pl.mateuszteteruk.composeplayground.modifiers.waveBackground
import pl.mateuszteteruk.composeplayground.ui.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .waveBackground(color = Color.Red),
                ) {
                    val time by produceState(0f) {
                        while (true) {
                            withInfiniteAnimationFrameMillis {
                                value = it / 1000f
                            }
                        }
                    }
                    Text(text = "Text: $time")
                }
            }
        }
    }
}
