// Adrián de Antonio Sanz y Erika Toledano Morgádez

package com.example.p3_top30games_adrian_erika

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.p3_top30games_adrian_erika.ui.theme.P3_Top30Games_adrian_erikaTheme


// GLOBAL VARIABLE Minecraft font
val minecraftFont = FontFamily(Font(R.font.minecraft_font))


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            P3_Top30Games_adrian_erikaTheme {
                val gradient = if (isSystemInDarkTheme()) {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1a1a2e),
                            Color(0xFF16213e),
                            Color(0xFF0f3460)
                        )
                    )
                } else {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE8F0FF),
                            Color(0xFF403F4C),
                            Color(0xFF8E9AAF)
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent // Fondo transparente
                    ) { innerPadding ->
                        Top30(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }

        }
    }
}

// Main function
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top30(modifier: Modifier = Modifier) {

    // Initialize an array of Top 30 games
    val games = listOf(
        Game(R.string.game1_title, R.drawable.g1, R.string.game1_year, R.string.game1_description, R.raw.s1),
        Game(R.string.game2_title, R.drawable.g2, R.string.game2_year, R.string.game2_description, R.raw.s2),
        Game(R.string.game3_title, R.drawable.g3, R.string.game3_year, R.string.game3_description, R.raw.s3),
        Game(R.string.game4_title, R.drawable.g4, R.string.game4_year, R.string.game4_description, R.raw.s4),
        Game(R.string.game5_title, R.drawable.g5, R.string.game5_year, R.string.game5_description, R.raw.s5),
        Game(R.string.game6_title, R.drawable.g6, R.string.game6_year, R.string.game6_description, R.raw.s6))

    // 1. Add top bar
    Scaffold(
        topBar = {
            TopAppBar(
                
                title = {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "logo",

                        )
                        Text(text = stringResource(R.string.title),
                            fontFamily = minecraftFont)
                    }
                }
            )
        }
    ){ paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(games) { index, game ->
                GameCard(game = game, position = index + 1)
            }
        }
    }

}

// Function to create a GameCard
@Composable
fun GameCard(game: Game, position: Int) {


    // Mutable expanded flag to expand information about game
    var expanded by remember { mutableStateOf(false) }

    // Get context to have access of resources
    val context = androidx.compose.ui.platform.LocalContext.current

    // Create one instance of mediaplayer that lives while composble exists
    val mediaPlayer = remember {
        MediaPlayer.create(context, game.musicRes)
    }

    // Release resources, executes when Composable disappear of screen, if not consumes RAM
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    // Card (container of each game)
    Card {
        // Column structure ssss
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            // 1. Position (index of array 1-30)
            Text(
                text = position.toString(),
                fontFamily = minecraftFont
            )

            // 2. Title of the game
            Text(
                text = stringResource(game.titleRes),
                fontFamily = minecraftFont
            )

            // 3. Box image
            Box(){
                // 3.1 Image
                Image(
                    painter = painterResource(game.imageRes),
                    contentDescription = stringResource(game.titleRes),
                    // When image gets pressed ==> reverse the value of val expanded
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                // Play music
                                expanded = true
                                mediaPlayer.start()
                                // Wait until you stop pressing
                                tryAwaitRelease()
                                // Pause music
                                expanded = false
                                mediaPlayer.pause()
                                mediaPlayer.seekTo(0)
                            }
                        )
                    }

                )
            }
            // ----------------------------------------------------------------------
            // All this 4. and 5. has to be hidden and get expanded bu clicking image

            if(expanded){
                Column(){
                    // 4. Year
                    Text(
                        text = stringResource(game.yearRes),
                        fontFamily = minecraftFont
                    )

                    // 5. Description
                    Text(
                        text = stringResource(game.descriptionRes),
                        fontFamily = minecraftFont
                    )
                }

            }


        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun Top30Preview() {
    P3_Top30Games_adrian_erikaTheme {
        Top30()
    }
}