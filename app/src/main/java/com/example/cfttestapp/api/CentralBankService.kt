package com.example.cfttestapp.api

import com.example.cfttestapp.pojo.ResponseFromCB
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * `CentralBankService`
 * - интерфейс сервиса взаимодействия с api центробанка реализуемый ретрофитом
 */
interface CentralBankService {
    /**
     * `fun getValuteCurse () : Single<ResponseFromCB>`
     *
     *     - Метод возвращает текущий курс валют обернутый в Single
     */
    @GET("/daily_json.js")
    fun getValuteCurse () : Single<ResponseFromCB>
}

