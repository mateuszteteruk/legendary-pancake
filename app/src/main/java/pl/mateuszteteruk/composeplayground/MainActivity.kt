package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import pl.mateuszteteruk.composeplayground.recipes.RecipeCard
import pl.mateuszteteruk.composeplayground.recipes.defaultRecipes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeCard(recipe = defaultRecipes.first())
        }
    }
}

@Preview
@Composable
fun DefaultRecipeCard() {
    RecipeCard(recipe = defaultRecipes.first())
}