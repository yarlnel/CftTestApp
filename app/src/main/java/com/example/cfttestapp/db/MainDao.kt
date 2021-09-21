package com.example.cfttestapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cfttestapp.pojo.ResponseFromCB

/**
 * `MainDao` - DAO [(Data Access Object)](https://ru.wikipedia.org/wiki/Data_Access_Object) - Объект доступа к данным
 */
@Dao
interface MainDao {

    @Insert
    fun saveResponseFromCB (responseFromCB: ResponseFromCB)

    @Query(value = "SELECT * FROM response_from_cb_list")
    fun getAllResponseFromCB () : List<ResponseFromCB>

    @Query(value = """
        SELECT * FROM response_from_cb_list
        ORDER BY id DESC LIMIT 1
        """)
    fun getLastResponseFromCB () : ResponseFromCB

}