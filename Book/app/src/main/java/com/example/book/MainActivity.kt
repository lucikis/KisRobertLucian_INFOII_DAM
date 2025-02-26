package com.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.example.book.ui.theme.BookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookTheme {
                Book()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Book(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
    ) {
        Text(
            "Scufita Rosie",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp),
            fontSize = 30.sp
        )
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    actions = {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            TextButton(
                                onClick = { navController.navigate("chapter1") }
                            ) {
                                Text("Chapter 1")
                            }
                            TextButton(
                                onClick = { navController.navigate("chapter2") }
                            ) {
                                Text("Chapter 2")
                            }
                            TextButton(
                                onClick = { navController.navigate("chapter3") }
                            ) {
                                Text("Chapter 3")
                            }
                            TextButton(
                                onClick = { navController.navigate("chapter4") }
                            ) {
                                Text("Chapter 4")
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(navController, startDestination = "chapter1") {
                    composable("chapter1") { Chapter1Screen() }
                    composable("chapter2") { Chapter2Screen() }
                    composable("chapter3") { Chapter3Screen() }
                    composable("chapter4") { Chapter4Screen() }
                }
            }
        }
    }
}

@Composable
fun Chapter1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Capitolul 1: Scufița Roșie și sarcina primită de la mama sa", modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
            fontSize = 18.sp
        )
        Text(stringResource(R.string.chapter1), modifier = Modifier
            .padding(horizontal = 10.dp),
            fontSize = 13.sp
        )
        val image = painterResource(R.drawable.scufita_pe_drum)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 8.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Chapter2Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Capitolul 2: Întâlnirea cu lupul și planul acestuia", modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
            fontSize = 18.sp
        )
        Text(stringResource(R.string.chapter2), modifier = Modifier
            .padding(horizontal = 10.dp),
            fontSize = 13.sp
        )
        val image = painterResource(R.drawable.scufita_lupul)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 8.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Chapter3Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Capitolul 3: Lupul la casa bunicii", modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
            fontSize = 18.sp
        )
        Text(stringResource(R.string.chapter3), modifier = Modifier
            .padding(horizontal = 10.dp),
            fontSize = 13.sp
        )
        val image = painterResource(R.drawable.lupul_intra)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 8.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Chapter4Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Capitolul 4: Salvarea Scufiței Roșii și pedepsirea lupului", modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
            fontSize = 18.sp
        )
        Text(stringResource(R.string.chapter4), modifier = Modifier
            .padding(horizontal = 10.dp),
            fontSize = 13.sp
        )
        val image = painterResource(R.drawable.pedeapsa_lupului)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 8.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookTheme {
        Book()
    }
}