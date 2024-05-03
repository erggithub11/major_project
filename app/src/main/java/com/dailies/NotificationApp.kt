package com.dailies
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

/**
 * This class is required for notification service to be implemented. It sets the basic importance and id
 * This class requires AndroidManifesto to be edited to contain notification service
 */
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