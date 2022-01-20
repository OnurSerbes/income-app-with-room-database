package com.example.incomeexpenseapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpenseapp.R
import com.example.incomeexpenseapp.model.Income
import kotlinx.android.synthetic.main.income_card.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var incomeList = emptyList<Income>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.income_card,parent,false))
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val currentItem = incomeList[position]
        holder.itemView.text_value.text = currentItem.value.toString()
        holder.itemView.text_detail.text = currentItem.detail.toString()
        holder.itemView.text_date.text= currentItem.date.toString()

        holder.itemView.income_cardView.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

    fun setData(income: List<Income>){
        this.incomeList = income
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}