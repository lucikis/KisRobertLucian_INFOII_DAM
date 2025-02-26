package com.example.counter

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterTheme {
                Counter()
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier.fillMaxSize().wrapContentSize()) {
    val context = LocalContext.current
    var counter by remember { mutableStateOf(0) }
    Column(
        modifier = modifier.wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter += 1}) {
            Text(stringResource(R.string.count_button))
        }
        Text(
            text = "Counter: $counter",
            modifier = Modifier.padding(8.dp)
        )
        Button(onClick = { Toast.makeText(context, "Hello!", Toast.LENGTH_SHORT).show()}) {
            Text(stringResource(R.string.toast_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    CounterTheme {
        Counter()
    }
}