package com.example.uloha2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.uloha2.databinding.FragmentListBinding
import com.google.gson.GsonBuilder

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var binding: FragmentListBinding
    lateinit var data: MyList
    var sharedPref: SharedPreferences? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        val value = sharedPref?.getString("myObject", null)
        if (value != null) {
            data = GsonBuilder().create().fromJson(value, MyList::class.java)
        } else {
            data = MyList(ArrayList())
//            saveData(data, sharedPref)
//            val jsonString = GsonBuilder().create().toJson(data)
//            sharedPref?.edit()?.putString("myObject", jsonString)?.apply()
        }

        Log.d("DEBUG", data.myList.toString())

//        val data1 = arrayListOf<Item>(
//            Item("title1", "test1", "12.12.2021", false),
//            Item("title2", "test2", "12.12.2020", true),
//            Item("title3", "test3", "12.12.2019", false),
//            Item("title4", "test4", "12.10.2021", true),
//            Item("title5", "test5", "12.05.2021", false),
//            Item("title6", "test6", "12.12.2021", false),
//
//            )

        val cont = context as MainActivity
        val myAdapter = MyAdapter(cont, data.myList)
        binding.listView.adapter = myAdapter

        binding.newItem.setOnClickListener {
            saveData(data, sharedPref)
            val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            model.sendMessage("")
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment, AddFragment())
                commit()
            }
        }

        binding.delete.setOnClickListener {
            val toRemove = data.myList.filter {
                it.checked
            }
            data.myList.removeAll(toRemove)
            saveData(data, sharedPref)
            myAdapter.notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()
        saveData(data, sharedPref)
    }

    fun saveData(data: MyList, sharedPref: SharedPreferences?) {
        val jsonString = GsonBuilder().create().toJson(data)
        sharedPref?.edit()?.putString("myObject", jsonString)?.apply()
    }
}