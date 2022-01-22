package com.example.uloha2

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.uloha2.databinding.ListItemBinding
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter(private val context: Activity, private val myList: ArrayList<Item>):
    ArrayAdapter<Item>(context, R.layout.list_item, myList) {

    lateinit var binding: ListItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item, null)

        binding = ListItemBinding.bind(view)

        val title: TextView = binding.title
        val date: TextView = binding.textDate
        val checked: CheckBox = binding.checkB
        val backgr: ConstraintLayout = binding.constrLayout
        val descText: TextView = binding.descTextView

        title.text = myList[position].title
        date.text = myList[position].date
        checked.isChecked = myList[position].checked
        descText.text = myList[position].text

        checkCheck(myList[position], backgr)

        binding.checkB.setOnCheckedChangeListener { buttonView, isChecked ->
            myList[position].checked = isChecked
            checkCheck(myList[position], backgr)
        }

        if (!checked.isChecked && checkDate(myList[position].date)) backgr.setBackgroundResource(R.color.red)

        return view
    }

    fun checkDate(dateString: String) : Boolean {

        val cal = Calendar.getInstance()
        val dateList = dateString.split(".").toList()
        if (dateList[2].toInt() < cal.get(Calendar.YEAR)) return true
        else if (dateList[1].toInt() < cal.get(Calendar.MONTH)) return true
        else if (dateList[0].toInt() < cal.get(Calendar.DAY_OF_MONTH)) return true

        return false


    //        if (dateList[0].toInt() < cal.get(Calendar.DAY_OF_MONTH) &&
//                dateList[1].toInt() < cal.get(Calendar.MONTH) &&
//                dateList[2].toInt() < cal.get(Calendar.YEAR)) bgr.setBackgroundResource(R.color.red)
//
//        val bol = dateList[0].toInt() < cal.get(Calendar.DAY_OF_MONTH)
//        Log.d("LIST: ", "I am HERE ${dateList[0]} ${cal.get(Calendar.DAY_OF_MONTH)} ${bol}")
//        Log.d("DATE", cal.get(Calendar.DAY_OF_MONTH).toString())
//        Log.d("DATE", cal.get(Calendar.MONTH).toString())
    }

    fun checkCheck(item: Item, bgr: ConstraintLayout) {
        if (item.checked) {
            bgr.setBackgroundResource(R.color.green)
        }
        else {
            if (!checkDate(item.date))
            bgr.setBackgroundResource(R.color.white)
            else bgr.setBackgroundResource(R.color.red)
        }
    }
}