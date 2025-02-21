package com.example.lab3_4

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab3_4.ui.theme.Lab3_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3_4Theme {
                ButtonClick()
            }
        }
    }
}

@Composable
fun ButtonClick(modifier: Modifier = Modifier.fillMaxSize().wrapContentSize()){
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Button(onClick = {Toast.makeText(context, "The button has been clicked", Toast.LENGTH_SHORT).show()}) {
          Text(stringResource(R.string.button))
      }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonClickPreview() {
    Lab3_4Theme {
        ButtonClick()
    }
}