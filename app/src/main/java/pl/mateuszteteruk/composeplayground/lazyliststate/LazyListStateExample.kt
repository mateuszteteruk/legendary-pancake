package pl.mateuszteteruk.composeplayground.lazyliststate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LazyListStateExample() {
    val state: LazyListState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = state.elevationComplex()
        ) {
            Text(text = "LazyListStateExample", modifier = Modifier.padding(8.dp))
        }
        LazyColumn(
            state = state,
        ) {
            items(50) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(40.dp),
                    text = "Item #$it"
                )
            }
        }
    }
}

private val LazyListState.elevationSimple: Dp
    get() = if (firstVisibleItemIndex == 0) {
        minOf(firstVisibleItemScrollOffset.toFloat().dp / 3, LIFTED_ELEVATION)
    } else {
        LIFTED_ELEVATION
    }

@Composable
private fun LazyListState.elevationComplex(): Dp {
    val firstVisibleItemInfo = layoutInfo.visibleItemsInfo.firstOrNull()
    return when {
        firstVisibleItemIndex != 0 -> LIFTED_ELEVATION
        firstVisibleItemIndex == 0 && firstVisibleItemInfo != null -> {
            val offset = firstVisibleItemScrollOffset.toFloat() / firstVisibleItemInfo.size.toFloat()
            LIFTED_ELEVATION * offset
        }
        else -> DEFAULT_ELEVATION
    }
}

private val DEFAULT_ELEVATION = 0.dp
private val LIFTED_ELEVATION = 24.dp

@Composable
private fun LazyListState.backgroundColor(): Color {
    val firstVisibleItemInfo = layoutInfo.visibleItemsInfo.firstOrNull()
    val fraction = when {
        firstVisibleItemIndex != 0 -> 1F
        firstVisibleItemIndex == 0 && firstVisibleItemInfo != null ->
            firstVisibleItemScrollOffset.toFloat() / firstVisibleItemInfo.size.toFloat()
        else -> 0F
    }
    return lerp(
        start = MaterialTheme.colors.primary,
        stop = MaterialTheme.colors.secondary,
        fraction = fraction
    )
}
