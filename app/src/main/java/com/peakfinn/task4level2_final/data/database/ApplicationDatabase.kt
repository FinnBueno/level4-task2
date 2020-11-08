package com.peakfinn.task4level2_final.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peakfinn.assignment2.data.database.MatchDao
import com.peakfinn.task4level2_final.data.database.converter.RoundActionConverter
import com.peakfinn.task4level2_final.data.database.converter.RoundResultConverter
import com.peakfinn.task4level2_final.data.Match

/**
 * Created by Finn Bon on 26/03/2020.
 */
@Database(entities = [Match::class], version = 1, exportSchema = false)
@TypeConverters(RoundActionConverter::class, RoundResultConverter::class)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun matchDao(): MatchDao

    companion object{
        private const val DATABASE_NAME = "ROCK_PAPER_SCISSORS_DATABASE"

        @Volatile
        private var databaseInstance: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase? {
            if (databaseInstance == null) {
                synchronized(ApplicationDatabase::class) {
                    if (databaseInstance == null) {
                        databaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ApplicationDatabase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return databaseInstance
        }
    }

}