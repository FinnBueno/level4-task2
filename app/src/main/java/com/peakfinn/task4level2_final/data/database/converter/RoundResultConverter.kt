package com.peakfinn.task4level2_final.data.database.converter

import androidx.room.TypeConverter
import com.peakfinn.task4level2_final.data.database.converter.DatabaseConverter
import com.peakfinn.task4level2_final.util.RoundResult

/**
 * Created by Finn Bon on 26/03/2020.
 */
class RoundResultConverter: DatabaseConverter<RoundResult, String> {
    @TypeConverter
    override fun to(from: RoundResult): String = from.toString()
    @TypeConverter
    override fun from(to: String): RoundResult = RoundResult.valueOf(to)
}