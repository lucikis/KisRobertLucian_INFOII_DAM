package com.example.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activities.ui.theme.ActivitiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivitiesTheme {
                Activity1()
            }
        }
    }
}

@Composable
fun Activity1(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val image = painterResource(R.drawable.lamborghini)

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
        )
        Text(text = "Lamborghini",
            fontSize = 30.sp,
            modifier = Modifier
                .padding(bottom = 30.dp)
        )
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(
                    onClick = {
                        val intent = Intent(context, Activitatea3::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(start = 30.dp, end = 20.dp)
                ) {
                    Icon(
                        painterResource((R.drawable.back_arrow)),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                }
                Text(text = "Previous", modifier = Modifier.padding(top = 4.dp))
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(
                    onClick = {
                        val intent = Intent(context, Activitatea2::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(start = 30.dp, end = 20.dp)
                ) {
                    Icon(
                        painterResource((R.drawable.next_arrow)),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                }
                Text(text = "Next", modifier = Modifier.padding(start = 10.dp, top = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActivitiesTheme {
        Activity1()
    }
}