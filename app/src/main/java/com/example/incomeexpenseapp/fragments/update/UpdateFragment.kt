package com.example.incomeexpenseapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.incomeexpenseapp.R
import com.example.incomeexpenseapp.model.Income
import com.example.incomeexpenseapp.viewmodel.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_add.view.edit_text_value
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mIncomeViewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        //Initialize the viewModel
        mIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]

        view.edit_update_value.setText(args.currentIncome.value.toString())
        view.edit_update_detail.setText(args.currentIncome.detail)

        view.btn_update.setOnClickListener {
            updateItem()
        }

        view.btn_delete.setOnClickListener {
            deleteItem()
        }

        return view
    }

    private fun deleteItem() {
        println("delete user pressed")
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mIncomeViewModel.deleteIncome(args.currentIncome)
            Toast.makeText(requireContext(), "Successfully removed", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete")
        builder.show()
    }

    private fun updateItem() {
        val value = edit_update_value.text.toString()
        val detail = edit_update_detail.text.toString()
        val date = getCurrentDate()

        if (inputCheck(value, detail)) {
            //Create Income Object
            val updatedIncome = Income(args.currentIncome.id,Integer.parseInt(value.toString()), detail, date)
            //Update Current Income
            mIncomeViewModel.updateIncome(updatedIncome)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }
}

private fun getCurrentDate(): String {
//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(Date())
}

private fun inputCheck(value: String, detail: String): Boolean {
    return !(value.isEmpty() && TextUtils.isEmpty(detail))
}
