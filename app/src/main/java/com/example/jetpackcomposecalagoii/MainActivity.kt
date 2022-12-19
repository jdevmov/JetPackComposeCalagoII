package com.example.jetpackcomposecalagoii

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecalagoii.ui.theme.JetPackComposeCalagoIITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeCalagoIITheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colors.background
                ) {

                    SuperHeroGridView()

                        }
                    }
                }
            }
        }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        JetPackComposeCalagoIITheme {
            MyDivider()
        }
    }

    @Composable
    fun MyDivider() {
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp), color = Color.Red
        )
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun MyBadgeBox() {
        BadgedBox(badge = { Badge { Text("8") } }, modifier = Modifier.padding(16.dp)) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "")
        }
    }

    @Composable
    fun MyCard() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 12.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = Color.Red,
            contentColor = Color.Green,
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Ejemplo1")
                Text(text = "Ejemplo2")
                Text(text = "Ejemplo3")
            }
        }
    }


    @Composable
    fun MySwitch() {
        var state by rememberSaveable { mutableStateOf(true) }
        Switch(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = false,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.Red,
                checkedThumbColor = Color.Green,
                uncheckedTrackColor = Color.Magenta,
                checkedTrackColor = Color.Cyan,
                checkedTrackAlpha = 0.1f,
                uncheckedTrackAlpha = 0.1f,
                disabledCheckedThumbColor = Color.Yellow,
                disabledCheckedTrackColor = Color.Yellow
            )
        )
    }

    @Composable
    fun MyProgressAdvance() {
        var state by rememberSaveable { mutableStateOf(0f) }
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(progress = state)
            Row(Modifier.fillMaxWidth()) {
                Button(onClick = { state += 0.1f }) {
                    Text(text = "Incrementar")
                }


                Button(onClick = { state -= 0.1f }) {
                    Text(text = "Decrementar")
                }
            }
        }
    }

    @Composable
    fun MyProgress() {
        var showLoanding by rememberSaveable { mutableStateOf(false) }
        Column(
            Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showLoanding) {
                CircularProgressIndicator(color = Color.Red, strokeWidth = 8.dp)
                LinearProgressIndicator(
                    modifier = Modifier.padding(top = 32.dp),
                    color = Color.Red,
                    backgroundColor = Color.Green
                )
            }
            Button(onClick = { showLoanding = !showLoanding }) {
                Text(text = "Actualizando perfil")
            }
        }
    }

    @Composable
    fun MyIcon() {
        Icon(imageVector = Icons.Rounded.Star, contentDescription = "Iconos", tint = Color.Red)
    }

    @Composable
    fun MyImageAdvance() {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Ejemplo",
            modifier = Modifier
                .clip(CircleShape)
                .border(5.dp, Color.Red, CircleShape)
        )
    }

    @Composable
    fun MyImage() {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Ejemplo",
            alpha = 0.5f // opacidad
        )
    }

    @Composable
    fun MyButtonExample() {
        var enabled by rememberSaveable { mutableStateOf(true) }
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = { enabled = false },
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Cyan,
                    contentColor = Color.LightGray,
                ),
                border = BorderStroke(3.dp, Color.Blue)
            ) {
                Text(text = "Hola")
            }
            OutlinedButton(
                onClick = { enabled = false },
                enabled = enabled,
                modifier = Modifier.padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Cyan,
                    contentColor = Color.LightGray,
                    disabledBackgroundColor = Color.Red,
                    disabledContentColor = Color.Blue
                )
            ) {
                Text("Hola")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Hola")
            }
        }
    }

    @Composable
    fun MyTextFieldOutLined() {
        var myText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = myText,
            onValueChange = { myText = it },
            modifier = Modifier.padding(24.dp),
            label = { Text("Holita") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.Green
            )
        )
    }

    @Composable
    fun MyTextFieldAvance() {
        var myText by remember { mutableStateOf("") } //Esto es tener un estado que gestionar
        TextField(value = myText, onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
        },
            label = { Text("Introduce tu nombre") })
    }


    @Composable
    fun MyTextField(name: String, onValueChanged: (String) -> Unit) {

        TextField(value = name, onValueChange = { onValueChanged(it) })

    }

    @Composable
    fun MyText() {
        Column(Modifier.fillMaxSize()) {
            Text("Esto es un ejemplo")
            Text("Esto es un ejemplo", color = Color.Blue)
            Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
            Text("Esto es un ejemplo", fontFamily = FontFamily.Cursive)
            Text("Esto es un ejemplo", textDecoration = TextDecoration.LineThrough)
            Text("Esto es un ejemplo", textDecoration = TextDecoration.Underline)
            Text(
                "Esto es un ejemplo",
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline
                    )
                )
            )
            Text(text = "Esto es un ejemplo", fontSize = 30.sp)
        }
    }

