package com.example.greetings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetings.ui.theme.GreetingsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsTheme {
                MainGreetingsView()
            }
        }
    }
}



@Composable
fun MainView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Blue.copy(alpha = 0.3f)),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LoveToCode()

            MyIcon(45.0f)

            GreetingsText()

            TextDemo()

            MyIcon(-45.0f, color = Color.Blue)
        }
    }
}

@Composable
private fun TextDemo() {
    RedText("Let's learn something")

    BigText("New and Exciting!")

    ItalicText("Let's code everyday!")

    TextShadow("Let's experiment!")

    BoldText("Have fun and feel free to ask questions anytime. Coding is awesome!")

    CenterText("Remember, every line of code you write brings you one step closer to mastering your craft. Embrace the challenges, enjoy the process, and don’t be afraid to ask questions or seek help when you need it. Each mistake is just a stepping stone on your path to success.")
}

@Composable
private fun GreetingsText() {
    Text(
        text = "Greetings!",
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Blue, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 24.dp, vertical = 12.dp),
        color = Color.White
    )
}

@Composable
private fun MyIcon(degrees: Float,color: Color = Color.Red) {
    Icon(
        imageVector = Icons.Default.Face,
        contentDescription = null,
        modifier = Modifier
            .size(64.dp)
            .rotate(degrees),
        tint = color
    )
}

@Composable
private fun LoveToCode() {
    val borderWidth = 2.0.dp
    val padding = 5.dp

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp)
            .border(borderWidth, Color.Blue, shape = RoundedCornerShape(10.dp))
            .padding(padding)
            .border(borderWidth, Color.Red, shape = RoundedCornerShape(10.dp))
            .padding(padding)
            .border(borderWidth, Color.Black, shape = RoundedCornerShape(10.dp))
            .padding(padding)
    ) {
        MyText("I", color = Color.Red)
        MyText("love", color = Color.Magenta)
        MyText("to", color = Color.Blue)
        MyText("code", color = Color.DarkGray)
    }
}

@Composable
fun RedText(text: String = "Hello World!") {
    Text(text, color = Color.Red)
}

@Composable
fun BigText(text: String = "Hello World!") {
    Text(text, fontSize = 30.sp)
}

@Composable
fun ItalicText(text: String = "Hello World!") {
    Text(text, fontStyle = FontStyle.Italic)
}

@Composable
fun BoldText(text: String = "Hello World!") {
    Text(text, fontWeight = FontWeight.Bold)
}

@Composable
fun TextShadow(text: String = "Hello World!") {
    val offset = Offset(5.0f, 5.0f)
    val color = Color.Green.copy(alpha = 0.3f)
    Text(
        text,
        style = TextStyle(
            fontSize = 36.sp,
            shadow  =  Shadow(
                color = color,
                offset = offset,
                blurRadius = 3.0f
            )
        )
    )
}

@Composable
fun CenterText(text: String = "Hello World!") {
    Text(
        text,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 1.sp
    )
}


@Composable
fun MyText(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold
    )
}


@Composable
fun MainGreetingsView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Blue.copy(alpha = 0.3f)),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleView()
            Spacer(modifier = Modifier.weight(1f))
            MessageView()
            Spacer(modifier = Modifier.weight(2f))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingsTheme {
//        MainView()
        MainGreetingsView()
    }
}