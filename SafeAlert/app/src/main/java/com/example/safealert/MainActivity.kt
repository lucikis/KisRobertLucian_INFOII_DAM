package com.example.safealert

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.safealert.ui.theme.SafeAlertTheme


class MainActivity : ComponentActivity() {
    private lateinit var batteryReceiver: BatteryReceiver

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkAndRequestPermissions()

        batteryReceiver = BatteryReceiver()
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(batteryReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(batteryReceiver, filter)
        }

        setContent {
            SafeAlertTheme {
                AlertScreen(
                    onCall = { makeEmergencyCall() }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun checkAndRequestPermissions() {
        val requiredPermissions = arrayOf(
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )

        val missingPermissions = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missingPermissions.isNotEmpty()) {
            requestPermissionLauncher.launch(missingPermissions.toTypedArray())
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.SEND_SMS] == true) {
                Toast.makeText(this, "Permisiune SMS acordata!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permisiune SMS refuzata!", Toast.LENGTH_LONG).show()
            }
        }

    private fun makeEmergencyCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val callIntent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:0774677395")
            }
            startActivity(callIntent)
        } else {
            Toast.makeText(this, "Permisiune apel telefonic refuzata!", Toast.LENGTH_LONG).show()
        }
    }

}

@Composable
fun AlertScreen(onCall: () -> Unit) {
    var isAlertActive by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(10) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        val image = painterResource(R.drawable.danger_icon)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            )
            if (!isAlertActive) {
                Button(
                    onClick = {
                        isAlertActive = true
                        timer = object : CountDownTimer(10_000, 1_000) {
                            override fun onTick(millisUntilFinished: Long) {
                                timeLeft = (millisUntilFinished / 1000).toInt()
                            }

                            override fun onFinish() {
                                onCall()
                                isAlertActive = false
                            }
                        }.start()
                    },
                    modifier = Modifier.padding(top = 10.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Activeaza alerta")
                }
            } else {
                Button(
                    onClick = {
                        timer?.cancel()
                        isAlertActive = false
                    },
                    modifier = Modifier.padding(top = 10.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Anuleaza alerta")
                }
                Text(
                    text = "Timp ramas: $timeLeft secunde",
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlertScreen() {
    SafeAlertTheme {
        AlertScreen(onCall = {})
    }
}