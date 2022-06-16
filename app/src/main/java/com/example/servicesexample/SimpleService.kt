package com.example.servicesexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class SimpleService : Service() {

    /**
     *The onStartCommand() method is called every time we start the service either by calling startService() or startForegroundService().
     * This is where we want to define what the service will do.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            Log.e("Service", "Service is running...")
            try {
                Thread.sleep(5000)
                Log.e("Service", "task completed!")
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}