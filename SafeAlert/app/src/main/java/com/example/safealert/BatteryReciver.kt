package com.example.safealert

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.telephony.SmsManager
import android.widget.Toast
import android.location.Location
import android.location.LocationManager
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.Manifest

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_CHANGED) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPct = level * 100 / scale.toFloat()

            if (batteryPct <= 10) {
                sendLowBatteryAlert(context)
            }
        }
    }

    private fun sendLowBatteryAlert(context: Context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            val emergencyContact = "0774677395"
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(
                emergencyContact, null,
                "Bateria telefonului este sub 10%! Locatia finala: ${getLastKnownLocation(context)}",
                null, null
            )
            Toast.makeText(context, "SMS trimis contactului de urgenta!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Permisiune SMS refuzata!", Toast.LENGTH_LONG).show()
        }
    }

    private fun getLastKnownLocation(context: Context): String {
        val hasFineLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasFineLocation && !hasCoarseLocation) {
            return "Permisiune de locație refuzata"
        }

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
            !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return "Servicii de locație dezactivate"
        }

        return try {
            val location = getBestLastKnownLocation(locationManager)
            location?.let {
                "Latitudine: ${"%.5f".format(it.latitude)}, Longitudine: ${"%.5f".format(it.longitude)}"
            } ?: "Locatie indisponibilă"
        } catch (e: SecurityException) {
            "Eroare de permisiune"
        } catch (e: Exception) {
            "Eroare la obtinerea locatiei"
        }
    }

    private fun getBestLastKnownLocation(locationManager: LocationManager): Location? {
        val providers = listOf(
            LocationManager.GPS_PROVIDER,
            LocationManager.NETWORK_PROVIDER,
            LocationManager.PASSIVE_PROVIDER
        )

        var bestLocation: Location? = null

        providers.forEach { provider ->
            try {
                locationManager.getLastKnownLocation(provider)?.let { location ->
                    if (bestLocation == null || location.accuracy < bestLocation!!.accuracy) {
                        bestLocation = location
                    }
                }
            } catch (e: SecurityException) {
            }
        }

        return bestLocation
    }
}