package com.example.textshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textshowcase.ui.theme.TextShowCaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun NicerGreeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun TextModifiersDemo(modifier: Modifier = Modifier) {
    Text(
        text = "Hello, Compose! Hello, Compose! Hello, Compose! Hello, Compose! Hello, Compose! Hello, Compose!",
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(14.dp),
        color = Color.Blue,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.headlineMedium

    )
}

@Composable
fun DecoratedTextExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Hello, Elmo!",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Hello, Cookie Monster!",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = TextDecoration.LineThrough
            ),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Hello, Big Bird!",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = TextDecoration.None
            ),
            modifier = Modifier.padding(8.dp)
        )

        // Combine
        Text(
            text = "Hello, Bert and Ernie!",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                ),
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun TextWithShadowAndShape(name: String) {
    val roundedCornerShape = RoundedCornerShape(16.dp)
    Text(
        text = "Hello, $name!",
        style = MaterialTheme.typography.bodyLarge.copy(
            color = Color.White
        ),
        modifier = Modifier
            .padding(16.dp)
            .shadow(
                elevation = 8.dp,
                shape = roundedCornerShape
            )
            .background(
                color = Color.Red,
                shape = roundedCornerShape
            )
            .padding(16.dp)
    )
}

@Composable
fun ClickableText(name: String, modifier: Modifier = Modifier) {
    val roundedCornerShape = RoundedCornerShape(16.dp)
    var isRed by remember { mutableStateOf(true) }

    Text(
        text = "Hello, $name!",
        modifier = Modifier
            .clickable {
                isRed = !isRed
            }
            .padding(16.dp)
            .background(
                color = if (isRed) Color.Red.copy(alpha = 0.3f) else Color.Blue.copy(alpha = 0.3f),
                shape = roundedCornerShape
            )
            .border(
                width = 2.dp,
                color = if (isRed) Color.Red.copy(alpha = 0.3f) else Color.Blue.copy(alpha = 0.3f),
                shape = roundedCornerShape
            )
            .padding(16.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun GradientText(name: String, modifier: Modifier = Modifier) {
    val gradientColors = listOf(
        Color.Red, Color.Magenta, Color.Yellow
    )
    Text(
        text = "Hello $name! Enjoy this gradient text.",
        style = TextStyle(
            brush = Brush.linearGradient(colors = gradientColors),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun ShadowText(name: String, modifier: Modifier = Modifier) {
    var radius by remember { mutableStateOf(6f) }

    Text(
        text = "Hello $name! Enjoy this shadow and blur text.",
        style = TextStyle(
            shadow = Shadow(
                color = Color.Gray,
                offset = Offset(4f, 4f),
                blurRadius = radius
            ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        ),
        modifier = modifier
            .padding(8.dp)
            .clickable {
                radius = if (radius >= 20f) 6f else radius + 2f
            }
    )
}

@Composable
fun RichGreeting(name: String) {
    val greetingText = buildAnnotatedString {
        append("Hello ")

        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(name)
        }

        append("!")

        withStyle(
            style = SpanStyle(
                color = Color.Magenta,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        ) {
            append(" ✌ Peace and Love!")
        }

    }

    Text(
        text = greetingText,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    MaterialTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
//                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                RichGreeting("Big Bird")
                ShadowText("Alice")
                GradientText("Bert")
                Greeting("Alice")
                NicerGreeting("Bob")
                Greeting("Oscar")
                TextModifiersDemo()
                DecoratedTextExamples()
                TextWithShadowAndShape("Bert and Ernie")
                ClickableText("Oscar")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}