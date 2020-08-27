package com.example.alarmmanager

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(activity, activity as TimePickerDialog.OnTimeSetListener, hour,minutes,DateFormat.is24HourFormat(activity))
    }
}