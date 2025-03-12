package com.example.intentsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intentsapp.ui.theme.IntentsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsAppTheme {
                Intents()
            }
        }
    }
}

@Composable
fun Intents(modifier: Modifier = Modifier){
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("Email"))
                putExtra(Intent.EXTRA_SUBJECT, "Subject")
                putExtra(Intent.EXTRA_TEXT, "Text")
            }
            context.startActivity(emailIntent)
        },
        modifier
        ) {
            Text("Send an Email")
        }
        Button(
            onClick = {
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:0774677395")
                }
                context.startActivity(dialIntent)
            },
            modifier = modifier
                .padding(top = 10.dp)
        ) {
            Text("Dial Phone Number")
        }
        Button(
            onClick = {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://umfst.ro/e-umfst/"))
                context.startActivity(webIntent)
            },
            modifier = modifier
                .padding(top = 10.dp)
        ) {
            Text("Go to Website")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentsAppTheme {
        Intents()
    }
}