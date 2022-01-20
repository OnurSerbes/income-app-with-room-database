package com.example.incomeexpenseapp.repisotory

import androidx.lifecycle.LiveData
import com.example.incomeexpenseapp.data.IncomeDao
import com.example.incomeexpenseapp.model.Income

class IncomeRepository (private val incomeDao: IncomeDao) {

    val readAllData: LiveData<List<Income>> = incomeDao.readAllData()

    suspend fun addIncome(income: Income){
        incomeDao.addIncome(income)
    }

    suspend fun updateIncome(income: Income){
        incomeDao.updateIncome(income)
    }

    suspend fun deleteIncome(income: Income){
        incomeDao.deleteIncome(income)
    }

    suspend fun deleteAllIncomes(){
        incomeDao.deleteAllIncomes()
    }

     fun getValues(): LiveData<List<Int>> {
         return incomeDao.getValues()
    }

    fun getTotalValue(): Int{
        return incomeDao.getTotalValue()
    }


}