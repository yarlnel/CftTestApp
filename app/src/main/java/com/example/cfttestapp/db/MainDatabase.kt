package com.example.cfttestapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cfttestapp.pojo.ResponseFromCB
import com.example.cfttestapp.pojo.Valute

@Database(
    entities = [
        ResponseFromCB::class,
        Valute::class,
        ],

    version = 1
)
@TypeConverters(StringToValuteMapConvector::class)
abstract class MainDatabase : RoomDatabase () {
    abstract fun mainDao () : MainDao
}