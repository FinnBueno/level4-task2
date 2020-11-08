package com.peakfinn.assignment2.data.database

import android.content.Context
import com.peakfinn.task4level2_final.data.Match
import com.peakfinn.task4level2_final.data.database.ApplicationDatabase

/**
 * Created by Finn Bon on 26/03/2020.
 */
class MatchRepository(context: Context) {

    private val dao: MatchDao

    init {
        val db = ApplicationDatabase.getDatabase(context)
        dao = db!!.matchDao()
    }

    suspend fun getAllMatches(): List<Match> = dao.all()

    suspend fun storeMatch(match: Match) = dao.insert(match)

    suspend fun clear() = dao.clear()

    suspend fun getWonMatches() = dao.getWonMatches()

    suspend fun getDrawMatches() = dao.getDrawMatches()

    suspend fun getLostMatches() = dao.getLostMatches()
}