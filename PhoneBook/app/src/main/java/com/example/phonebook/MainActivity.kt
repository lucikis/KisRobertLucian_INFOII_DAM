package com.example.phonebook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonebook.ui.theme.PhoneBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhoneBookTheme {
                PhoneBook()
            }
        }
    }
}

@Composable
fun PhoneBook(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val contacts = remember {
        mutableStateListOf(
            Contact("Lionel", "Messi", "123456789", "123 Main St"),
            Contact("Lamine", "Yamal", "987654321", "456 Elm St"),
            Contact("Robert", "Lewandowski", "112233445", "789 Oak St"),
            Contact("Mohammed", "Salah", "818618469", "121 Torino St")
        )
    }

    val addLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { intent ->
                val name = intent.getStringExtra("name") ?: return@let
                val surname = intent.getStringExtra("surname") ?: return@let
                val phone = intent.getStringExtra("phone") ?: return@let
                val email = intent.getStringExtra("email") ?: return@let
                contacts.add(Contact(name, surname, phone, email))
            }
        }
    }

    val editLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { intent ->
                val oldPhone = intent.getStringExtra("oldPhone") ?: return@let
                val newName = intent.getStringExtra("name") ?: return@let
                val newSurname = intent.getStringExtra("surname") ?: return@let
                val newPhone = intent.getStringExtra("phone") ?: return@let
                val newEmail = intent.getStringExtra("email") ?: return@let

                val index = contacts.indexOfFirst { it.phone == oldPhone }
                if (index != -1) {
                    contacts[index] = Contact(newName, newSurname, newPhone, newEmail)
                }
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Phone book",
            fontSize = 35.sp,
            modifier = Modifier.padding(top = 30.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 50.dp),
            horizontalAlignment = Alignment.Start
        ) {
            LazyColumn {
                items(contacts) { contact ->
                    ContactItem(contact) { selectedContact ->
                        val intent = Intent(context, EditContactActivity::class.java).apply {
                            putExtra("name", selectedContact.name)
                            putExtra("surname", selectedContact.surname)
                            putExtra("phone", selectedContact.phone)
                            putExtra("email", selectedContact.email)
                        }
                        editLauncher.launch(intent)
                    }
                }
            }

            Spacer(modifier = Modifier.height(240.dp))

            Button(
                onClick = {
                    val intent = Intent(context, AddContactActivity::class.java)
                    addLauncher.launch(intent)
                },
                modifier = Modifier
                    .padding(start = 100.dp)
            ) {
                Text("Add contact")
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact, onEdit: (Contact) -> Unit) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${contact.name} ${contact.surname}",
            fontSize = 15.sp,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        TextButton(
            onClick = {
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${contact.phone}")
                }
                context.startActivity(callIntent)
            }
        ) {
            Icon(
                painterResource(R.drawable.call_logo),
                contentDescription = "Call",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }

        TextButton(
            onClick = { onEdit(contact) }
        ) {
            Icon(
                painterResource(R.drawable.edit_logo),
                contentDescription = "Edit",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhoneBookPreview() {
    PhoneBookTheme {
        PhoneBook()
    }
}