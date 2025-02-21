package com.example.virtuallight

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.virtuallight.ui.theme.VirtualLightTheme
import java.time.LocalTime

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VirtualLightTheme {
                VirtualLight()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VirtualLight(modifier: Modifier = Modifier.fillMaxSize().wrapContentSize()) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val currentTime = LocalTime.now()
    val isMorning = currentTime.isAfter(LocalTime.of(7, 0)) && currentTime.isBefore(LocalTime.of(18, 0))
    val isEvening = currentTime.isAfter(LocalTime.of(18,0)) && currentTime.isBefore(LocalTime.of(22, 0))
    val isNight = currentTime.isAfter(LocalTime.of(22,0)) && currentTime.isBefore(LocalTime.of(7, 0))
    var lightIsOn by remember { mutableStateOf(false) }
    var backgroundColor by remember { mutableStateOf(Color.DarkGray) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            lightIsOn = !lightIsOn
            if(lightIsOn){
                backgroundColor = Color.Yellow
            }else{
                backgroundColor = Color.DarkGray
            }
        }) {
            Text(stringResource(R.string.light_switch_button))
        }
        Button(onClick = {
            if(isMorning){
                backgroundColor = Color.White
                Toast.makeText(context, "Good morning! Do you need more light?", Toast.LENGTH_SHORT).show()
            }else if(isEvening){
                backgroundColor = Color(0xFFFFA500)
            }else if(isNight){
                backgroundColor = Color.DarkGray
                Toast.makeText(context, "Good night! The light has turned off automatically", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(stringResource(R.string.automatic_mode_button))
        }
        Button(onClick = {
            expanded = !expanded
        }) {
            Text(stringResource(R.string.custom_mode_button))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            DropdownMenuItem(
                text = {Text("White")},
                onClick = {
                    backgroundColor = Color.White
                    Toast.makeText(context, "Neutral mode activated!", Toast.LENGTH_SHORT).show()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Blue")},
                onClick = {
                    backgroundColor = Color.Blue
                    Toast.makeText(context, "Relaxation mode activated!", Toast.LENGTH_SHORT).show()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Red")},
                onClick = {
                    backgroundColor = Color.Red
                    Toast.makeText(context, "Alert mode activated!", Toast.LENGTH_SHORT).show()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Green")},
                onClick = {
                    backgroundColor = Color.Green
                    Toast.makeText(context, "Happy mode activated!", Toast.LENGTH_SHORT).show()
                    expanded = false
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VirtualLightTheme {
        VirtualLight()
    }
}