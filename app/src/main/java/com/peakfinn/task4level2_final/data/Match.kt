package com.peakfinn.task4level2_final.data

import android.os.Parcelable
import androidx.room.*
import com.peakfinn.task4level2_final.util.RoundAction
import com.peakfinn.task4level2_final.util.RoundResult
import kotlinx.android.parcel.Parcelize

/**
 * Created by Finn Bon on 26/03/2020.
 */
@Parcelize
@Entity(tableName = "match")
data class Match (
    @ColumnInfo(name = "result")
    val result: RoundResult,
    @ColumnInfo(name = "player_action")
    val playerAction: RoundAction,
    @ColumnInfo(name = "computer_action")
    val computerAction: RoundAction,
    @ColumnInfo(name = "played_at")
    val played_at: Long,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
): Parcelable