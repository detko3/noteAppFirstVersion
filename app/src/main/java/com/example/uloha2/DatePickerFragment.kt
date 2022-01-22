package com.example.uloha2

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import java.util.*

class DatePickerFragment() : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var dateStr = ""

    lateinit var model: SharedViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        val cont = context as MainActivity
        return DatePickerDialog(cont, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        dateStr = "$day.${month}.${year}"
//        val dd = Date(year, month, day)
//        val dd = Calendar.getInstance()
//        dd.set(year, month, day)
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.sendMessage("$day.${month}.${year}")
//        model.sendMessage(dd)
    }

//    fun getDateString(): String {
//        return dateStr
//    }
}
