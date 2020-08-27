package com.example.alarmmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class NotificationHelper(base: Context) : ContextWrapper(base) {

    private var mManager: NotificationManager? = null

    companion object{
        const val channel1Id = "channel1Id"
        const val channel1Name = "Channel 1"
        const val channel2Id = "channel2Id"
        const val channel2Name = "Channel 2"
    }

    init {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel1 = NotificationChannel(channel1Id, channel1Name,NotificationManager.IMPORTANCE_HIGH)
        channel1.enableLights(true)
        channel1.enableVibration(true)
        channel1.lightColor = R.color.colorPrimary
        channel1.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getNotificationManager().createNotificationChannel(channel1)

        val channel2 = NotificationChannel(channel2Id, channel2Name,NotificationManager.IMPORTANCE_HIGH)
        channel2.enableLights(true)
        channel2.enableVibration(true)
        channel2.lightColor = R.color.colorPrimary
        channel2.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getNotificationManager().createNotificationChannel(channel2)
    }

    fun getNotificationManager():NotificationManager{
        if(mManager == null){
            mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return mManager!!
    }

    fun getChannel1Notification (title:String,message:String):NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channel1Id)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_notification1)
    }

    fun getChannel2Notification (title:String,message:String):NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channel2Id)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_notification2)
    }



}