package com.dailies

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Handler
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class NotificationService(
    private val context:Context
){
    private val notificationManager=context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification(){
        val notification=NotificationCompat.Builder(context,"channel_id")
            .setContentTitle("Content Title")
            .setContentText("Text basic")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }


    fun showDailiesNotification(
        name: String,
        description: String,
        hour: Int,
        minute: Int
    ){
        var hourText = hour.toString()
        var minuteText = minute.toString()
        val notification=NotificationCompat.Builder(context,"channel_id")
            .setContentTitle("${hourText}"+ ":" + "${minuteText}"+ " " + "${name}" )
            .setContentText(description)
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun show30MinDailiesNotification(
        name: String,
        description: String,
        hour: Int,
        minute: Int
    ){
        var hourText = hour.toString()
        var minuteText = minute.toString()
        val notification=NotificationCompat.Builder(context,"channel_id")
            .setContentTitle("${hourText}"+ ":" + "${minuteText}"+ " " + "${name}" )
            .setContentText(description)
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        Handler().postDelayed({
            notificationManager.notify(
                Random.nextInt(),
                notification
            )
        }, 1800000)

    }

    fun showExpandableNotification(){
        val notification=NotificationCompat.Builder(context,"channel_id")
            .setContentTitle("Content Title")
            .setContentText("Expandable text")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(
                        context.bitmapFromResource(
                            R.drawable.logo
                        )
                    )
            )
            .build()
        notificationManager.notify(Random.nextInt(),notification)
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId:Int
    )= BitmapFactory.decodeResource(
        resources,
        resId
    )
}