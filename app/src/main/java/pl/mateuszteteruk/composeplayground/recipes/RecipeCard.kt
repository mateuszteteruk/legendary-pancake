package pl.mateuszteteruk.composeplayground.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCard(recipe: Recipe, modifier: Modifier) {
    Surface(modifier = modifier, shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = recipe.imageResource),
                contentDescription = recipe.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = recipe.title, style = MaterialTheme.typography.h4)
                recipe.ingredients.forEach {
                    Text("- $it")
                }
            }
        }
    }
}