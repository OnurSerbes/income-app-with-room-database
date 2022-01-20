package com.example.incomeexpenseapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.incomeexpenseapp.data.IncomeDatabase
import com.example.incomeexpenseapp.model.Income
import com.example.incomeexpenseapp.repisotory.IncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Income>> //if anything happens by the private of this variable make it public again
    private val repository: IncomeRepository

    init {
        val incomeDao = IncomeDatabase.getDatabase(application).incomeDao()
        repository = IncomeRepository(incomeDao)
        readAllData = repository.readAllData
    }

    fun addIncome(income: Income){
        viewModelScope.launch(Dispatchers.IO){
            repository.addIncome(income)
        }
    }

    fun updateIncome(income: Income){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateIncome(income)
        }
    }

    fun deleteIncome(income: Income){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIncome(income)
        }
    }

    fun deleteAllIncomes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllIncomes()
        }
    }

//    fun getValues(){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getValues()
//        }
//    }

    fun getValues(): LiveData<List<Int>>{
        return repository.getValues()
    }

    fun getTotalValue(): Int{
        return repository.getTotalValue()
    }

//        fun getTotalValue(){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getTotalValue()
//        }
//    }

}