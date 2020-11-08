package com.peakfinn.task4level2_final.data.database.converter

/**
 * Created by Finn Bon on 26/03/2020.
 */
interface DatabaseConverter<F, T> {
    fun to(from: F): T
    fun from(to: T): F
}