package com.example.stopwatchapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stopwatchapp.ui.theme.StopWatchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StopWatchAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StopwatchScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun StopwatchScreen(modifier: Modifier = Modifier) {
        var timeElapsed by remember { mutableStateOf(0L) }
        var isRunning by remember { mutableStateOf(false) }
        val handler = remember { Handler(Looper.getMainLooper()) }

        val updateTime = remember {
            object : Runnable {
                override fun run() {
                    if (isRunning) {
                        timeElapsed += 10
                        handler.postDelayed(this, 10)
                    }
                }
            }
        }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatTime(timeElapsed),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Button(
                    onClick = {
                        if (!isRunning) {
                            isRunning = true
                            handler.post(updateTime)
                        }
                    },
                    enabled = !isRunning ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff1b1d7a),
                        contentColor = Color.White
                    )
                ) {
                    Text("Start")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {
                        isRunning = false
                        handler.removeCallbacks(updateTime)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff1b1d7a),
                        contentColor = Color.White
                    ),
                    enabled = isRunning
                ) {
                    Text("Pause")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = {
                        isRunning = false
                        handler.removeCallbacks(updateTime)
                        timeElapsed = 0L
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff1b1d7a),
                        contentColor = Color.White
                    )
                ) {
                    Text("Reset")
                }
            }
        }
    }

    private fun formatTime(milliseconds: Long): String {
        val seconds = (milliseconds / 1000) % 60
        val millis = (milliseconds % 1000) / 10
        return String.format("%02d:%02d", seconds, millis)
    }

    @Preview(showBackground = true)
    @Composable
    fun StopwatchPreview() {
        StopWatchAppTheme {
            StopwatchScreen()
        }
    }
}