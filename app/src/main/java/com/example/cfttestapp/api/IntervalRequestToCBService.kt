package com.example.cfttestapp.api

import com.example.cfttestapp.db.MainDao
import com.example.cfttestapp.pojo.ResponseFromCB
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate

class IntervalRequestToCBService
@Inject constructor(
    private val centralBankService: CentralBankService,
    private val mainDao: MainDao
    ) {

    private val timer = Timer()


    val service
    get ()  : Observable<ResponseFromCB>
            = Observable.create { emitter ->
                    // проводим запрос каждые сто секунд
                    timer.scheduleAtFixedRate(0, 100000) {
                        val disposable = centralBankService . getValuteCurse() . subscribe ({
                                it?.let { responseFromCB ->
                                    emitter.onNext(responseFromCB)

                                    // сохраняем данные в базу если день изменился
                                    mainDao.getLastResponseFromCB() ?. date . let { lastDate ->
                                        if (lastDate.isNullOrEmpty())
                                            mainDao.saveResponseFromCB(responseFromCB)
                                        else if (lastDate != responseFromCB.date)
                                            mainDao.saveResponseFromCB(responseFromCB)
                                    }

                                }
                        }, {}) .dispose()
                    }

            }


    fun stopService () {
        timer.cancel()
    }
}