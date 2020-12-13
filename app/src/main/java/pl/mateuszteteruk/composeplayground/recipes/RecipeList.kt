package pl.mateuszteteruk.composeplayground.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeList(recipes: List<Recipe>) {
    val cardModifier = Modifier.padding(16.dp)
    LazyColumnFor(items = recipes) { item ->
        RecipeCard(recipe = item, modifier = cardModifier)
    }
}