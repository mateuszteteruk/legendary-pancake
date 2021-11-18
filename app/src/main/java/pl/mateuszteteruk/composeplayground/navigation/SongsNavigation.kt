package pl.mateuszteteruk.composeplayground.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.util.UUID

sealed class Destination(
    val route: String,
) {

    object Songs : Destination(route = "songs")

    object Song : Destination(route = "song/{songId}") {

        fun createRoute(songId: String): String =
            "song/$songId"
    }
}

@Composable
fun SongsNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Songs.route,
    ) {
        composable(route = Destination.Songs.route) {
            Songs(
                onSongClick = { song ->
                    navController.navigate(
                        Destination.Song.createRoute(song.id)
                    )
                }
            )
        }
        composable(
            route = Destination.Song.route,
            arguments = listOf(
                navArgument("songId") {
                    type = NavType.StringType
                }
            )
        ) {
            val song = requireNotNull(it.arguments?.getString("songId"))
            SongDetails(song)
        }
    }
}

data class Song(
    val id: String,
    val artist: String,
    val name: String,
)

@Composable
fun Songs(
    songs: List<Song> = listOfSongs,
    onSongClick: (Song) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    ) {
        items(items = songs, key = { it.id }) {
            SongItem(song = it, onSongClick = onSongClick)
        }
    }
}

@Composable
fun SongItem(song: Song, onSongClick: (Song) -> Unit) {
    Row(modifier = Modifier
        .clickable { onSongClick(song) }
        .fillMaxWidth()
        .padding(12.dp)
    ) {
        Column {
            Text(text = song.name)
            Text(text = song.artist)
        }
    }
}

@Composable
fun SongDetails(songId: String) {
    // resolve song
    val song = listOfSongs.first { it.id == songId }
    Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Text(text = "${song.name} by ${song.artist}")
    }
}

val listOfSongs: List<Song> by lazy {
    listOf(
        Song(UUID.randomUUID().toString(), "Sanah", "Królowa dram"),
        Song(UUID.randomUUID().toString(), "Taconafide", "TOKYO2020"),
        Song(UUID.randomUUID().toString(), "Quebonafide", "Matcha Latte"),
    )
}
