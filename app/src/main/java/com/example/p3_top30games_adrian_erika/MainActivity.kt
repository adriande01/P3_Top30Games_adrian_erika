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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
// * Import VerticalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p3_top30games_adrian_erika.ui.theme.P3_Top30Games_adrian_erikaTheme
import com.example.p3_top30games_adrian_erika.ui.theme.Pink40


// GLOBAL VARIABLE Minecraft font
val minecraftFont = FontFamily(Font(R.font.minecraft_font))


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            P3_Top30Games_adrian_erikaTheme {

                // Background Fade
                val gradient = if (isSystemInDarkTheme()) {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF6A6B72),
                            Color(0xFF2C2C2D),
                            Color(0xFF6A6B72)

                        )
                    )
                } else {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFDFE3F3),
                            Color(0xFF6A6B72),
                            Color(0xFFDFE3F3),
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
                        containerColor = Color.Transparent
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
        Game(
            R.string.game1_title,
            R.drawable.g1,
            R.string.game1_year,
            R.string.game1_description,
            R.raw.s1
        ),
        Game(
            R.string.game2_title,
            R.drawable.g2,
            R.string.game2_year,
            R.string.game2_description,
            R.raw.s2
        ),
        Game(
            R.string.game3_title,
            R.drawable.g3,
            R.string.game3_year,
            R.string.game3_description,
            R.raw.s3
        ),
        Game(
            R.string.game4_title,
            R.drawable.g4,
            R.string.game4_year,
            R.string.game4_description,
            R.raw.s4
        ),
        Game(
            R.string.game5_title,
            R.drawable.g5,
            R.string.game5_year,
            R.string.game5_description,
            R.raw.s5
        ),
        Game(
            R.string.game6_title,
            R.drawable.g6,
            R.string.game6_year,
            R.string.game6_description,
            R.raw.s6
        ),
        Game(
            R.string.game7_title,
            R.drawable.g7,
            R.string.game7_year,
            R.string.game7_description,
            R.raw.s7
        ),
        Game(
            R.string.game8_title,
            R.drawable.g8,
            R.string.game8_year,
            R.string.game8_description,
            R.raw.s8
        ),
        Game(
            R.string.game9_title,
            R.drawable.g9,
            R.string.game9_year,
            R.string.game9_description,
            R.raw.s9
        ),
        Game(
            R.string.game10_title,
            R.drawable.g10,
            R.string.game10_year,
            R.string.game10_description,
            R.raw.s10
        ),
        Game(
            R.string.game11_title,
            R.drawable.g11,
            R.string.game11_year,
            R.string.game11_description,
            R.raw.s11
        ),
        Game(
            R.string.game12_title,
            R.drawable.g12,
            R.string.game12_year,
            R.string.game12_description,
            R.raw.s12
        ),
        Game(
            R.string.game13_title,
            R.drawable.g13,
            R.string.game13_year,
            R.string.game13_description,
            R.raw.s13
        ),
        Game(
            R.string.game14_title,
            R.drawable.g14,
            R.string.game14_year,
            R.string.game14_description,
            R.raw.s14
        ),
        Game(
            R.string.game15_title,
            R.drawable.g15,
            R.string.game15_year,
            R.string.game15_description,
            R.raw.s15
        ),
        Game(
            R.string.game16_title,
            R.drawable.g16,
            R.string.game16_year,
            R.string.game16_description,
            R.raw.s16
        ),
        Game(
            R.string.game17_title,
            R.drawable.g17,
            R.string.game17_year,
            R.string.game17_description,
            R.raw.s17
        ),
        Game(
            R.string.game18_title,
            R.drawable.g18,
            R.string.game18_year,
            R.string.game18_description,
            R.raw.s18
        ),
        Game(
            R.string.game19_title,
            R.drawable.g19,
            R.string.game19_year,
            R.string.game19_description,
            R.raw.s19
        ),
        Game(
            R.string.game20_title,
            R.drawable.g20,
            R.string.game20_year,
            R.string.game20_description,
            R.raw.s20
        ),
        Game(
            R.string.game21_title,
            R.drawable.g21,
            R.string.game21_year,
            R.string.game21_description,
            R.raw.s21
        ),
        Game(
            R.string.game22_title,
            R.drawable.g22,
            R.string.game22_year,
            R.string.game22_description,
            R.raw.s22
        ),
        Game(
            R.string.game23_title,
            R.drawable.g23,
            R.string.game23_year,
            R.string.game23_description,
            R.raw.s23
        ),
        Game(
            R.string.game24_title,
            R.drawable.g24,
            R.string.game24_year,
            R.string.game24_description,
            R.raw.s24
        ),
        Game(
            R.string.game25_title,
            R.drawable.g25,
            R.string.game25_year,
            R.string.game25_description,
            R.raw.s25
        ),
        Game(
            R.string.game26_title,
            R.drawable.g26,
            R.string.game26_year,
            R.string.game26_description,
            R.raw.s26
        ),
        Game(
            R.string.game27_title,
            R.drawable.g27,
            R.string.game27_year,
            R.string.game27_description,
            R.raw.s27
        ),
        Game(
            R.string.game28_title,
            R.drawable.g28,
            R.string.game28_year,
            R.string.game28_description,
            R.raw.s28
        ),
        Game(
            R.string.game29_title,
            R.drawable.g29,
            R.string.game29_year,
            R.string.game29_description,
            R.raw.s29
        ),
        Game(
            R.string.game30_title,
            R.drawable.g30,
            R.string.game30_year,
            R.string.game30_description,
            R.raw.s30
        )

        )

    // Create PagerState for VerticalPager
    val pagerState = rememberPagerState(pageCount = { games.size })

    // 1. Add top bar
    Scaffold(
        topBar = {
            val isDarkTheme = isSystemInDarkTheme()

            // Sets the TopAppBar background color based on the current system theme
            val backgroundColorTopBar = if (isDarkTheme) {
                Color(0xFF4E405E)
            } else {
                Color(0xFFB59DC7)
            }

            val borderColorTopBar = if (isDarkTheme) {
                Color(0xFFB59DC7)
            } else {
                Color(0xFF4E405E)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(2.5.dp, borderColorTopBar),
                    colors = CardDefaults.cardColors(
                        containerColor = backgroundColorTopBar
                    )

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(120.dp)
                        )
                        Text(
                            text = stringResource(R.string.title),
                            fontFamily = minecraftFont,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        // Replace LazyColumn with VerticalPager
        VerticalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            // Each page shows one GameCard
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                GameCard(
                    game = games[page],
                    position = page + 1
                )
            }
        }
    }

}

// Function to create a GameCard
@Composable
fun GameCard(game: Game, position: Int) {
    val isDarkTheme = isSystemInDarkTheme()

    // DEFAULT: Light theme
    // Text color for year and description (grayish white)
    var grayishWhite = Color(0xFF525151)
    // Sets the TopAppBar background color based on the current system theme
    var backgroundColorCards = Color(0xFFE4DDF8)
    var borderColorCards =  Color(0xFF826C91)



    if(isDarkTheme){
        // 1. Background color theme dark
        backgroundColorCards = Color(0xFF826C91)
        // 2. Border color theme dark
        borderColorCards = Color(0xFFE4DDF8)
        // 3. Description color theme dark
        grayishWhite = Color(0xFFCBC9C9)
    }



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
    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColorCards

        ),
        border = BorderStroke(2.5.dp, borderColorCards),
    ) {
        // Column structure
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Position (index of array 1-30)
            Text(
                text = position.toString(),
                fontFamily = minecraftFont,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 2. Title of the game
            Text(
                text = stringResource(game.titleRes),
                lineHeight = 40.sp,
                fontFamily = minecraftFont,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Box image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentAlignment = Alignment.Center
            ) {
                // 3.1 Image
                Image(
                    painter = painterResource(game.imageRes),
                    contentDescription = stringResource(game.titleRes),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
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

            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    // 4. Year

                    Text(
                        text = stringResource(game.yearRes),
                        fontFamily = minecraftFont,
                        fontSize = 18.sp,
                        color = grayishWhite,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    // Space between year and description
                    Spacer(modifier = Modifier.height(8.dp))

                    // 5. Description
                    Text(
                        text = stringResource(game.descriptionRes),
                        fontFamily = minecraftFont,
                        fontSize = 16.sp,
                        color = grayishWhite,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
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