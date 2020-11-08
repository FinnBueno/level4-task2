package com.peakfinn.task4level2_final.data.database.converter

import androidx.room.TypeConverter
import com.peakfinn.task4level2_final.data.database.converter.DatabaseConverter
import com.peakfinn.task4level2_final.util.RoundAction

/**
 * Created by Finn Bon on 26/03/2020.
 */
class RoundActionConverter: DatabaseConverter<RoundAction, String> {
    @TypeConverter
    override fun to(from: RoundAction): String = from.toString()
    @TypeConverter
    override fun from(to: String): RoundAction = RoundAction.valueOf(to)
}