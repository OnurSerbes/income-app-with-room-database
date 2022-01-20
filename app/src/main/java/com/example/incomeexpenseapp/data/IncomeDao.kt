package com.example.incomeexpenseapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.incomeexpenseapp.model.Income

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIncome(income: Income)

    @Update
    suspend fun updateIncome(income: Income)

    @Delete
    suspend fun deleteIncome(income: Income)

    @Query("DELETE FROM income_table")
    suspend fun deleteAllIncomes()

    @Query("SELECT * FROM income_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Income>>

    @Query("SELECT value FROM income_table ")
    fun getValues(): LiveData<List<Int>>

    @Query("SELECT SUM(value) FROM income_table ")
    fun getTotalValue(): Int

}