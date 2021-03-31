package pl.mateuszteteruk.composeplayground.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeList(recipes: List<Recipe>) {
    val cardModifier = Modifier.padding(16.dp)
    LazyColumn {
        recipes.forEach { recipe ->
            item {
                RecipeCard(recipe = recipe, modifier = cardModifier)
            }
        }
    }
}