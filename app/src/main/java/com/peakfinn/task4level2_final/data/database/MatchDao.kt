package com.peakfinn.assignment2.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.peakfinn.task4level2_final.data.Match

/**
 * Created by Finn Bon on 26/03/2020.
 */
@Dao
interface MatchDao {
    @Query("SELECT * FROM `match`")
    suspend fun all(): List<Match>

    @Insert
    suspend fun insert(entity: Match)

    @Query("DELETE FROM `match`")
    suspend fun clear()

    @Query("SELECT COUNT(id) FROM `match` WHERE result = 'WON'")
    suspend fun getWonMatches(): Int

    @Query("SELECT COUNT(id) FROM `match` WHERE result = 'DRAW'")
    suspend fun getDrawMatches(): Int

    @Query("SELECT COUNT(id) FROM `match` WHERE result = 'LOSE'")
    suspend fun getLostMatches(): Int
}