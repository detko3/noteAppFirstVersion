package com.example.uloha2

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uloha2.databinding.FragmentAddBinding
import com.google.gson.GsonBuilder

class AddFragment : Fragment(R.layout.fragment_add) {

    lateinit var binding: FragmentAddBinding

    lateinit var data: MyList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val value = sharedPref?.getString("myObject", null)
        Log.d("PROBLEM ", "${value}")
        data = GsonBuilder().create().fromJson(value, MyList::class.java)


        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.message.observe(viewLifecycleOwner, Observer {
            binding.dateTextView.text = it
        })

        binding.addBtn.setOnClickListener {
            binding.apply {
                data.myList.add(Item(
                    titleText.text.toString(),
                    textAddText.text.toString(),
                    dateTextView.text.toString(),
                    false)
                )
            }
            val jsonString = GsonBuilder().create().toJson(data)
            sharedPref?.edit()?.putString("myObject", jsonString)?.apply()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment, ListFragment())
                commit()
            }
        }

        binding.addDate.setOnClickListener {
            val dateFragment = DatePickerFragment()
            dateFragment.show(parentFragmentManager, "datePicker")

        }

        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun afterTextChanged(s: Editable?) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.apply {
                    addBtn.isEnabled =
                        titleText.text?.isNotEmpty()?:false &&
                                textAddText.text?.isNotEmpty()?:false &&
                                dateTextView.text?.isNotEmpty()?:false
                }
            }
        }

        binding.titleText.addTextChangedListener(textWatcher)
        binding.textAddText.addTextChangedListener(textWatcher)
        binding.dateTextView.addTextChangedListener(textWatcher)

    }

}