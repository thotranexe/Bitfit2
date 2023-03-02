package com.example.bitfit_part1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BitFitItemDao {
    @Query("SELECT * FROM bitFitItem_table")
    fun getAll(): Flow<List<BitFitEntity>>

    @Insert
    fun insertAll(bitFitItems: List<BitFitEntity>)

    @Insert
    fun insert(bitFitItem: BitFitEntity)

    @Query("DELETE FROM bitFitItem_table")
    fun deleteAll()
}