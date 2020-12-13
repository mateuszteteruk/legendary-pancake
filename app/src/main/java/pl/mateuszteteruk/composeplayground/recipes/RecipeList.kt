package pl.mateuszteteruk.composeplayground.recipes

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable

@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumnFor(items = recipes) { item ->
        RecipeCard(recipe = item)
    }
}