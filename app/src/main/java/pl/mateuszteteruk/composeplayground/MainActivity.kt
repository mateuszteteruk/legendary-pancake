package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import pl.mateuszteteruk.composeplayground.animatedfavbutton.AnimatedFavouriteButton
import pl.mateuszteteruk.composeplayground.ui.ComposePlaygroundTheme
import pl.mateuszteteruk.composeplayground.utils.CenteredColumn

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                CenteredColumn {
                    AnimatedFavouriteButton()
                }
            }
        }
    }
}