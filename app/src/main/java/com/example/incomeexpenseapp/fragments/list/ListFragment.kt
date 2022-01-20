package com.example.incomeexpenseapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Query
import com.example.incomeexpenseapp.R
import com.example.incomeexpenseapp.model.Income
import com.example.incomeexpenseapp.viewmodel.IncomeViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mIncomeViewModel: IncomeViewModel
//    private val totalValue = mIncomeViewModel.getTotalValue()
    private var totalValue: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mIncomeViewModel = ViewModelProvider(this)[IncomeViewModel::class.java]
        mIncomeViewModel.readAllData.observe(viewLifecycleOwner,Observer{ income ->
            adapter.setData(income)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

            editTotalValueText(view)
        //this will enable the actionbar menu layout
        setHasOptionsMenu(true)

        return view
    }

    //it will do the database operations in a separate Thread
    private fun editTotalValueText(view: View) {
        Thread {
            var totalValue: String = (mIncomeViewModel.getTotalValue()).toString()
            view.text_totalBudget.text = "Your total budget is $$totalValue"
        }.start()
    }

//    private fun getTotalValue() {
//        val incomeList: LiveData<List<Int>> = mIncomeViewModel.getValues()
//        for(i in 0..incomeList){
//            totalValue += incomeList[i]
//        }
//        println("the income list is: $incomeList")
//    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllIncomes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllIncomes() {
        println("delete user pressed")
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mIncomeViewModel.deleteAllIncomes()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything")
        builder.show()
    }

}

