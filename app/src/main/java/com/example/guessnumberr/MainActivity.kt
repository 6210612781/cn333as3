package com.example.guessnumberr

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessnumberr.ui.theme.GuessNumberrTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessNumberrTheme {
                    PlayGame()
                }
            }
        }
    }

var random: Int = Random.nextInt(1, 1000)

@Composable
fun PlayGame(){
    var textnum by remember { mutableStateOf("") }
    var hint by remember { mutableStateOf("Please enter your guess :") }
    var points by remember {mutableStateOf(0)}
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth()


    ){
        Text(
            text = "Number Guessing Game",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(50.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "$hint",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.Center

        ) {
            TextField(
                value = textnum,
                onValueChange = {
                    textnum = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.hint))
                },
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = 40.dp)
            )
            Button(
                modifier = Modifier
                    .alignByBaseline()
                    .padding(10.dp),
                onClick = {
                    val number: Int = textnum.toString().toInt()

                    if (number < random){
                        points ++
                        hint = "Wrong, Too low"

                    } else if (number > random) {
                        points ++
                        hint ="Wrong, Too high"

                    } else{

                        hint ="YOU WON, your number is right"

                    }
                    textnum = ""

                }

            ) {
                Text(text = stringResource(id = R.string.check))
            }
        }
    }



    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Guessing Number : $points",
            textAlign = TextAlign.Center,
            )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                random = Random.nextInt(1, 1000)
                hint = "Please enter your guess:"
                points = 0

            }
        ) {
            Text(text = stringResource(id = R.string.reset))
        }
    }
}
