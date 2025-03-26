package com.example.safealert

import android.app.Service
import android.content.*
import android.os.*
import android.widget.Toast

class PowerButtonService : Service() {
    private var powerPressCount = 0
    private var lastPressTime = 0L

    private val powerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_SCREEN_OFF) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastPressTime < 800) {
                    powerPressCount++
                    if (powerPressCount >= 3) {
                        activateAlert(context)
                        powerPressCount = 0
                    }
                } else {
                    powerPressCount = 1
                }
                lastPressTime = currentTime
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(powerReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(powerReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun activateAlert(context: Context?) {
        Toast.makeText(context, "Alerta activata prin butonul Power", Toast.LENGTH_LONG).show()
    }
}