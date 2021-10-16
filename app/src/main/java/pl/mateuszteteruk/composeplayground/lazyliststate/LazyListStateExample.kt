package pl.mateuszteteruk.composeplayground.lazyliststate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LazyListStateExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar {
            Text(text = "LazyListStateExample",modifier=Modifier.padding(8.dp))
        }
        LazyColumn {
            items(50) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Item #$it"
                )
            }
        }
    }
}
