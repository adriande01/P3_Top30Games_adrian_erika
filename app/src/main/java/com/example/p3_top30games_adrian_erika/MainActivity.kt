// Adrián de Antonio Sanz y Erika Toledano Morgádez

package com.example.p3_top30games_adrian_erika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
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
@Composable
fun Top30(modifier: Modifier = Modifier) {
    

}

// Function to create a GameCard
@Composable
fun GameCard(game: Game, position: Int) {
    // Minecraft font
    val minecraftFont = FontFamily(Font(R.font.minecraft_font))

    // Card (container of each game)
    Card {
        // Column structure
        Column {
            // 1. Position (index of array 1-30)
            Text(
                text = position.toString(),
                fontFamily = minecraftFont
            )

            // 2. Title of the game
            Text(
                text = stringResource(game.getTitleRes()),
                fontFamily = minecraftFont
            )

            // 3. Box image
            Box(){
                // 3.1 Image
                Image(
                    painter = painterResource(game.getImageRes()),
                    contentDescription = stringResource(game.getTitleRes())
                )
            }

            // 4. Year
            Text(
                text = stringResource(game.getYearRes()),
                fontFamily = minecraftFont
            )

            // 5. Description
            Text(
                text = stringResource(game.getDescriptionRes()),
                fontFamily = minecraftFont
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Top30Preview() {
    P3_Top30Games_adrian_erikaTheme {
        Top30()
    }
}