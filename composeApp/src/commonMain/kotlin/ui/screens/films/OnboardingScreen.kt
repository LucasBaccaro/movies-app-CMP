package ui.screens.films

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getPlatformName
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.components.BottomBar

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingScreen(navigator: Navigator) {
    val platformPaddingBottom = if (getPlatformName() == "iOS") 45.dp else 15.dp
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = { },
            bottomBar = {
                BottomBar(
                    "Ver trailer",
                    onClick = { navigator.navigate("/home") },
                    platformPaddingBottom
                )
            },
            backgroundColor = Color.Transparent
        ) { innerPadding ->

            Image(
                painter = painterResource("onboardingscreen.jpg"),
                contentDescription = "Imagen de fondo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource("Group 11.png"),
                    contentDescription = "Descripción de la imagen",
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Todo sobre peliculas, series, anime y mucho mas.",
                    style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 37.28.sp,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Para disfrutar del mejor contenido.",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.75.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                    )
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

