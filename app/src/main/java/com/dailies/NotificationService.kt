package com.dailies

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Handler
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

/**
 * This class holds many functions that outputs notification. This class required notification manager from notification app to activate
 */
class NotificationService(
    private val context:Context
){
    private val notificationManager=context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification(){
        val notification=NotificationCompat.Builder(context,"channel_id")
            /**
             * the compat builder allows the notification to be built to the developers liking
             */
            .setContentTitle("Content Title")
            .setContentText("Text basic")
            .setSmallIcon(R.drawable.logo)
            /**
             * IF the priority boolean was added to data then another function would be made with priority set as low
             */
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        /**
         * function below uses the notification manager to output the notification
         */
        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }


    /**
     * the function below is a highly modified version of the basic notification.
     * It takes in variables and output them as notification
     */
    fun showDailiesNotification(
        name: String,
        description: String,
        hour: Int,
        minute: Int
    ){
        val hourText = hour.toString()
        val minuteText = minute.toString()
        val notification=NotificationCompat.Builder(context,"channel_id")
            .setContentTitle(hourText + ":" + minuteText + " " + name)
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

    /**
     * function below is used in card and are used to notify the user after 30minutes
     */
    fun show30MinDailiesNotification(
        name: String,
        description: String,
        hour: Int,
        minute: Int
    ){
        val hourText = hour.toString()
        val minuteText = minute.toString()
        val notification=NotificationCompat.Builder(context,"channel_id")
            .setContentTitle(hourText + ":" + minuteText + " " + name)
            .setContentText(description)
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        /**
         * The postDelayed function allows the notification manager to print out the notification at a different time
         */
        Handler().postDelayed({
            notificationManager.notify(
                Random.nextInt(),
                notification
            )
        }, 1800000)

    }

    /**
     * An expandable notification created with intention for future use.
     */

    fun showExpandableNotification(){
        val notification=NotificationCompat.Builder(context,"channel_id")
            .setContentTitle("Content Title")
            .setContentText("Expandable text")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            /**
             * set style changes the notification design from the basic and big picture increases the size of the notification
             */
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

    /**
     * bit map resource is required to access the logo from drawable folder
     */
    private fun Context.bitmapFromResource(
        @DrawableRes resId:Int
    )= BitmapFactory.decodeResource(
        resources,
        resId
    )
}