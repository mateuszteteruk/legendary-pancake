package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import pl.mateuszteteruk.composeplayground.recipes.RecipeList
import pl.mateuszteteruk.composeplayground.recipes.defaultRecipes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeList(recipes = defaultRecipes)
        }
    }
}