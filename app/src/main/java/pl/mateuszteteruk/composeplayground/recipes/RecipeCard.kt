package pl.mateuszteteruk.composeplayground.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCard(recipe: Recipe) {
    Surface(modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
        val image = imageResource(id = recipe.imageResource)
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                bitmap = image,
                modifier = Modifier.fillMaxWidth().height(188.dp),
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