package com.example.servicesexample

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log


class SimpleForegroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            Log.e("ForegroundService", "Service is running...")
            try {
                Thread.sleep(5000)
                //To destroy the service
                stopSelf()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()

        // Create the Notification
        val CHANNELID = "Foreground Service ID"
        val notification: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNELID,
                CHANNELID,
                NotificationManager.IMPORTANCE_LOW
            )
            //Create Notification channel
            getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
            //Create Notification builder with channel id
            Notification.Builder(this, CHANNELID)
        } else {
            //Create Notification builder
            Notification.Builder(this)
        }

        // Set notification content(Title, Message and Icon)
        notification.apply {
            setContentText("Foreground Service is running...")
            setContentTitle("Foreground Service enabled")
            setSmallIcon(R.drawable.ic_lock_idle_alarm)
        }

        // Start the Foreground service with Notification. Notification is mandatory for Foreground services
        startForeground(1001, notification.build())

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("ForegroundService", "Foreground Service completed.")
    }
}