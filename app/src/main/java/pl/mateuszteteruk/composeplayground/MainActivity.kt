package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import pl.mateuszteteruk.composeplayground.lazyliststate.LazyListStateExample
import pl.mateuszteteruk.composeplayground.ui.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                LazyListStateExample()
            }
        }
    }
}
