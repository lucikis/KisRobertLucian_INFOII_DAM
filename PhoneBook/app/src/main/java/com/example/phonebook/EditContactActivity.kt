package com.example.phonebook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonebook.ui.theme.PhoneBookTheme

class EditContactActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhoneBookTheme {
                EditContact()
            }
        }
    }
}

@Composable
fun EditContact(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as? Activity

    val intent = (context as? Activity)?.intent
    var nameText by remember { mutableStateOf(intent?.getStringExtra("name") ?: "") }
    var surnameText by remember { mutableStateOf(intent?.getStringExtra("surname") ?: "") }
    var phoneText by remember { mutableStateOf(intent?.getStringExtra("phone") ?: "") }
    var emailText by remember { mutableStateOf(intent?.getStringExtra("email") ?: "") }
    val oldPhone = intent?.getStringExtra("phone") ?: ""

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Edit contact", fontSize = 30.sp, modifier = Modifier.padding(bottom = 20.dp))

        TextField(
            value = nameText,
            onValueChange = { nameText = it },
            label = { Text("Name") },
            modifier = Modifier.padding(10.dp)
        )
        TextField(
            value = surnameText,
            onValueChange = { surnameText = it },
            label = { Text("Surname") },
            modifier = Modifier.padding(10.dp)
        )
        TextField(
            value = phoneText,
            onValueChange = { phoneText = it },
            label = { Text("Phone") },
            modifier = Modifier.padding(10.dp)
        )
        TextField(
            value = emailText,
            onValueChange = { emailText = it },
            label = { Text("Email") },
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = {
                val resultIntent = Intent().apply {
                    putExtra("oldPhone", oldPhone)
                    putExtra("name", nameText)
                    putExtra("surname", surnameText)
                    putExtra("phone", phoneText)
                    putExtra("email", emailText)
                }
                activity?.setResult(Activity.RESULT_OK, resultIntent)
                activity?.finish()
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditContactPreview() {
    PhoneBookTheme {
        EditContact()
    }
}