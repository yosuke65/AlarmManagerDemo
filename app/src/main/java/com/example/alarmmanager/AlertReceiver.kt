package com.example.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlertReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationHelper = NotificationHelper(context)
        val title = "Alarm"
        val message = "Time to wake up"
        val builder = notificationHelper.getChannel1Notification(title, message)
        notificationHelper.getNotificationManager().notify(1,builder.build())
    }
}