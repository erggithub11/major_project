package com.dailies
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class NotificationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val notificationChannel=NotificationChannel(
            "channel_id",
            "channel_name",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}