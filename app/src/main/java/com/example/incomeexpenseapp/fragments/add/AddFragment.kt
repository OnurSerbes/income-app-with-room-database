package com.example.incomeexpenseapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.incomeexpenseapp.R
import com.example.incomeexpenseapp.model.Income
import com.example.incomeexpenseapp.viewmodel.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private lateinit var mIncomeViewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]

        view.btn_add.setOnClickListener {
            insertToDatabase()
        }

        return view
    }

    private fun insertToDatabase() {
        val value = edit_text_value.text.toString()
        val detail = edit_text_detail.text.toString()
        val date = getCurrentDate()

        if(inputCheck(value,detail)){
            //Create Income Object
            val income = Income(0,Integer.parseInt(value.toString()),detail,date)
            //Add Data to Database
            mIncomeViewModel.addIncome(income)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
        }
    }

    private fun getCurrentDate(): String {
//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }

    private fun inputCheck(value: String, detail: String): Boolean{
        return !(value.isEmpty() && TextUtils.isEmpty(detail) )
    }
