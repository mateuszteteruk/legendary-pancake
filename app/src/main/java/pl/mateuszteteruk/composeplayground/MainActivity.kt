package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.mateuszteteruk.composeplayground.dropdownmenu.DropdownMenuExample
import pl.mateuszteteruk.composeplayground.lazyliststate.LazyListStateExample
import pl.mateuszteteruk.composeplayground.ui.ComposePlaygroundTheme
import pl.mateuszteteruk.composeplayground.utils.CenteredColumn

class MainActivity : AppCompatActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DropdownMenuExample()
                }
            }
        }
    }
}
