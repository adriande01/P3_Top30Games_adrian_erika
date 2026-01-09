// Adrián de Antonio Sanz y Erika Toledano Morgádez

package com.example.p3_top30games_adrian_erika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.Preview
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.p3_top30games_adrian_erika.ui.theme.P3_Top30Games_adrian_erikaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            P3_Top30Games_adrian_erikaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Top30(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Main function
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top30(modifier: Modifier = Modifier) {
    // * THEME BLACK LIGHT
    // * Background fade out

    // Initialize an array of Top 30 games
    val games = listOf(
        Game(R.string.game1_title, R.drawable.g1, R.string.game1_year, R.string.game1_description),
        Game(R.string.game2_title, R.drawable.g2, R.string.game2_year, R.string.game2_description),
        Game(R.string.game3_title, R.drawable.g3, R.string.game3_year, R.string.game3_description),
        Game(R.string.game4_title, R.drawable.g4, R.string.game4_year, R.string.game4_description),
        Game(R.string.game5_title, R.drawable.g5, R.string.game5_year, R.string.game5_description),
        Game(R.string.game6_title, R.drawable.g6, R.string.game6_year, R.string.game6_description),
        Game(R.string.game7_title, R.drawable.g7, R.string.game7_year, R.string.game7_description),
        Game(R.string.game8_title, R.drawable.g8, R.string.game8_year, R.string.game8_description),
        Game(R.string.game9_title, R.drawable.g9, R.string.game9_year, R.string.game9_description),
        Game(R.string.game10_title, R.drawable.g10, R.string.game10_year, R.string.game10_description),
        Game(R.string.game11_title, R.drawable.g11, R.string.game11_year, R.string.game11_description),
        Game(R.string.game12_title, R.drawable.g12, R.string.game12_year, R.string.game12_description),
        Game(R.string.game13_title, R.drawable.g13, R.string.game13_year, R.string.game13_description),
        Game(R.string.game14_title, R.drawable.g14, R.string.game14_year, R.string.game14_description),
        Game(R.string.game15_title, R.drawable.g15, R.string.game15_year, R.string.game15_description),
        Game(R.string.game16_title, R.drawable.g16, R.string.game16_year, R.string.game16_description),
        Game(R.string.game17_title, R.drawable.g17, R.string.game17_year, R.string.game17_description),
        Game(R.string.game18_title, R.drawable.g18, R.string.game18_year, R.string.game18_description),
        Game(R.string.game19_title, R.drawable.g19, R.string.game19_year, R.string.game19_description),
        Game(R.string.game20_title, R.drawable.g20, R.string.game20_year, R.string.game20_description),
        Game(R.string.game21_title, R.drawable.g21, R.string.game21_year, R.string.game21_description),
        Game(R.string.game22_title, R.drawable.g22, R.string.game22_year, R.string.game22_description),
        Game(R.string.game23_title, R.drawable.g23, R.string.game23_year, R.string.game23_description),
        Game(R.string.game24_title, R.drawable.g24, R.string.game24_year, R.string.game24_description),
        Game(R.string.game25_title, R.drawable.g25, R.string.game25_year, R.string.game25_description),
        Game(R.string.game26_title, R.drawable.g26, R.string.game26_year, R.string.game26_description),
        Game(R.string.game27_title, R.drawable.g27, R.string.game27_year, R.string.game27_description),
        Game(R.string.game28_title, R.drawable.g28, R.string.game28_year, R.string.game28_description),
        Game(R.string.game29_title, R.drawable.g29, R.string.game29_year, R.string.game29_description),
        Game(R.string.game30_title, R.drawable.g30, R.string.game30_year, R.string.game30_description)
    )

    // 1. Add top bar
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = "logo"
                        )
                        Text(text = stringResource(R.string.title))
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
    // Minecraft font
    val minecraftFont = FontFamily(Font(R.font.minecraft_font))
    // Mutable expanded flag to expand information about game
    var expanded by remember { mutableStateOf(false) }

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
                    // When image gets clicked ==> reverse the value of val expanded
                    modifier = Modifier.clickable{
                        expanded = !expanded
                    }

                )
            }
            // ----------------------------------------------------------------------
            // All this 4. and 5. has to be hiden and get expanded bu clicking image


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