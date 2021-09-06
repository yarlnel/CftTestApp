package com.example.cfttestapp.api

import com.example.cfttestapp.pojo.ResponseFromCB
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET


interface CentralBankService {
    @GET("/daily_json.js")
    fun getValuteCurse () : Single<ResponseFromCB>
}

