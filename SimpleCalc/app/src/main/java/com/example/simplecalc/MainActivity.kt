package com.example.simplecalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplecalc.ui.theme.SimpleCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCalcTheme {
                SimpleCalc()
            }
        }
    }
}

@Composable
fun SimpleCalc(modifier: Modifier = Modifier) {
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var resultString by remember { mutableStateOf("") }
    var resultValue by remember { mutableStateOf(0f) }

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        TextField(
            value = firstNumber,
            onValueChange = { firstNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp, top = 90.dp)
        )
        TextField(
            value = secondNumber,
            onValueChange = { secondNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp, top = 10.dp)
        )
        Row(modifier = Modifier
        ) {
            Button(
                onClick = {
                    resultValue = firstNumber.toFloat()
                    resultValue += secondNumber.toFloat()
                    resultString = resultValue.toString()
                },
                modifier = Modifier
                    .padding(start = 60.dp, end = 10.dp, top = 20.dp),
                shape = RectangleShape
            ) {
                Text(
                    text = "Add"
                )
            }
            Button(
                onClick = {
                    resultValue = firstNumber.toFloat()
                    resultValue -= secondNumber.toFloat()
                    resultString = resultValue.toString()
                },
                modifier = Modifier
                    .padding(20.dp),
                shape = RectangleShape
            ) {
                Text(
                    text = "Sub"
                )
            }
        }
        Row(modifier = Modifier
        ) {
            Button(
                onClick = {
                    resultValue = firstNumber.toFloat()
                    resultValue /= secondNumber.toFloat()
                    resultString = resultValue.toString()
                },
                modifier = Modifier
                    .padding(start = 60.dp),
                shape = RectangleShape
            ) {
                Text(
                    text = "Div"
                )
            }
            Button(
                onClick = {
                    resultValue = firstNumber.toFloat()
                    resultValue *= secondNumber.toFloat()
                    resultString = resultValue.toString()
                },
                modifier = Modifier
                    .padding(start = 34.dp),
                shape = RectangleShape
            ) {
                Text(
                    text = "Mul"
                )
            }
        }
        Text(
            text = "Result: $resultString",
            modifier = Modifier
                .padding(start = 60.dp, top = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleCalcPreview() {
    SimpleCalcTheme {
        SimpleCalc()
    }
}