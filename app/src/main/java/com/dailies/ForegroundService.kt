package com.dailies

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class ForegroundService : Service(){


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }


    enum class Actions{
        START, STOP
    }

    private fun start(){
        val notification=NotificationCompat.Builder(this,"channel_id")
            .setContentTitle("Content Title")
            .setContentText("Text basic")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()
        startForeground(1,notification)
    }


}