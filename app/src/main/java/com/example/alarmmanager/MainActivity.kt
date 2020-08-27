package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TextView
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),TimePickerDialog.OnTimeSetListener {

    private lateinit var textViewTimer:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        textViewTimer = text_view_alarm_time
        button_timepicker.setOnClickListener{
            val timePicker = TimePickerFragment()
            timePicker.show(supportFragmentManager,"time picker")
        }

        button_cancel_alarm.setOnClickListener{
            cancelAlarm()
        }
    }

    private fun cancelAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this,AlertReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 101, intent,0)

        alarmManager.cancel(pendingIntent)
        text_view_alarm_time.text = "Alarm canceled"
    }

    override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minutes: Int) {
        var c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY,hourOfDay)
        c.set(Calendar.MINUTE,minutes)
        c.set(Calendar.SECOND, 0)

        updateTimeText(c)
        startAlarm(c)
    }

    private fun startAlarm(c: Calendar) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this,AlertReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 101, intent,0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.timeInMillis,pendingIntent)
    }

    private fun updateTimeText(c: Calendar) {
        var timeText = "Alarm set for: "
        timeText += java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(c.time)
        text_view_alarm_time.text = timeText
    }
}