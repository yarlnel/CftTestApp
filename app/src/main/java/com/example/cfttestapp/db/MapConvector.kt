package com.example.cfttestapp.db

import androidx.room.TypeConverter
import com.example.cfttestapp.pojo.Valute
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

object StringToValuteMapConvector {
    @JvmStatic
    @TypeConverter
    fun mapToJsonString (map: Map<String, Valute>) : String
        = Gson().toJson(map)

    @JvmStatic
    @TypeConverter
    fun jsonStringToMap (jsonString: String) : Map<String, Valute>
        = Gson().fromJson(
            jsonString,
            object : TypeToken<Map<String, Valute>> () {} . type
    )
}