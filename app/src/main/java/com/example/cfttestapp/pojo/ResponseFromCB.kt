package com.example.cfttestapp.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = "response_from_cb_list")
data class ResponseFromCB(

	@ColumnInfo(name = "previous_url")
	@field:SerializedName("PreviousURL")
	val previousURL: String? = null,

	@ColumnInfo(name = "timestamp")
	@field:SerializedName("Timestamp")
	val timestamp: String? = null,

	@ColumnInfo(name = "current_date")
	@field:SerializedName("Date")
	val date: String? = null,

	@ColumnInfo(name = "previous_date")
	@field:SerializedName("PreviousDate")
	val previousDate: String? = null,

	@ColumnInfo(name = "valute_map")
	@field:SerializedName("Valute")
	val valutes: Map<String, Valute>? = null,

	@ColumnInfo(name = "id")
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0

)

