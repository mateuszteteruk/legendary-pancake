package pl.mateuszteteruk.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import pl.mateuszteteruk.composeplayground.recipes.RecipeList
import pl.mateuszteteruk.composeplayground.recipes.defaultRecipes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(title = {
                    Text(text = "Recipes")
                })
                RecipeList(recipes = defaultRecipes)
            }
        }
    }
}