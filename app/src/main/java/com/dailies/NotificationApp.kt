package com.dailies

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class NotificationApp: Application(){
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            "channel_id",
            "channel name",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}