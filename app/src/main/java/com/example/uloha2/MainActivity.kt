package com.example.uloha2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uloha2.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFr = ListFragment()

        setFragment(listFr)

//        val data = arrayListOf<Item>(
//            Item("title1", "test1", "12.12.2021", false),
//            Item("title2", "test2", "12.12.2020", true),
//            Item("title3", "test3", "12.12.2019", false),
//            Item("title4", "test4", "12.10.2021", true),
//            Item("title5", "test5", "12.05.2021", false),
//            Item("title6", "test6", "12.12.2021", false),
//
//            )
//
//        binding.myListview.adapter = MyAdapter(this, data)
//
//
    }

    private fun setFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment, fragment)
        commit()
    }
}