package com.example.cfttestapp.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * `Valute` - pojo для определенной валюты
 */
@Entity
data class Valute (

    @ColumnInfo(name = "char_code")
    @field:SerializedName("CharCode")
    val charCode: String? = null,

    @ColumnInfo(name ="value")
    @field:SerializedName("Value")
    val value: Double? = null,

    @ColumnInfo(name = "previous")
    @field:SerializedName("Previous")
    val previous: Double? = null,

    @ColumnInfo(name = "string_valute_id")
    @field:SerializedName("ID")
    val stringId: String? = null,

    @ColumnInfo(name = "nominal")
    @field:SerializedName("Nominal")
    val nominal: Int? = null,

    @ColumnInfo(name = "num_code")
    @field:SerializedName("NumCode")
    val numCode: String? = null,

    @ColumnInfo(name = "valute_name")
    @field:SerializedName("Name")
    val name: String? = null,

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

    )
