package com.example.cfttestapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cfttestapp.R
import com.example.cfttestapp.api.CentralBankService
import com.example.cfttestapp.api.IntervalRequestToCBService
import com.example.cfttestapp.pojo.ResponseFromCB
import com.example.cfttestapp.pojo.Valute
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate

/**
 * `ValuteRecyclerViewAdapter` - Саммый раздутый класс в проекте, адаптер для RecyclerView
 */
class ValuteRecyclerViewAdapter
    @Inject constructor(
        private val cbService: IntervalRequestToCBService
        )
    : RecyclerView.Adapter<ValuteRecyclerViewAdapter.ValuteViewHolder> ()
{

// IntervalRequestToCBService
    private val valuteList = mutableListOf<Valute>()

    private val compositeDisposable = CompositeDisposable()

    private var rubleSum : Double? = null

    /**
     * `fun setRubleSum (sum: Double)`
     * - устанавливает сумму пользователя в рублях
     * - и реактивно обновляет все данные в RecyclerView
     */
    fun setRubleSum (sum: Double) {
        rubleSum = sum
        notifyDataSetChanged()
    }

    init {
        cbService
            .service
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       valuteList.clear()
                       valuteList.addAll(
                           it.valutes?.map { e -> e.value }
                           ?:
                           listOf(Valute()))

                    notifyDataSetChanged()
            },{})
            .let(compositeDisposable::add)

    }

    class ValuteViewHolder (itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        var charCodeTV : TextView?      = null
        var valuteNameTV : TextView?    = null
        var currentCurseTV : TextView?  = null
        var previousCurseTV : TextView? = null
        var sumTV : TextView?        = null

        init {
            charCodeTV =        itemView.findViewById(R.id.charCodeTV)
            valuteNameTV =      itemView.findViewById(R.id.valuteNameTV)
            currentCurseTV =    itemView.findViewById(R.id.currentCurseTV)
            previousCurseTV =   itemView.findViewById(R.id.previousCurseTV)
            sumTV =             itemView.findViewById(R.id.sumTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ValuteViewHolder(
        LayoutInflater
        .from(parent.context)
        .inflate(R.layout.valute_card_view, parent, false)
    )


    override fun onBindViewHolder(holder: ValuteViewHolder, position: Int) {
        holder . apply {
            valuteList [ position ] . apply {
                charCodeTV ?. text = charCode
                valuteNameTV ?. text = name

                nominal!!; previous!!; value!!

                currentCurseTV ?. text = String.format("%.2f", (value  / nominal))
                previousCurseTV ?. text = String.format("%.2f", (previous / nominal))

                rubleSum ?. let { sum ->
                    sumTV ?. text = String.format("%.2f", sum / (value / nominal))
                }
            }

        }
    }

    override fun getItemCount() =
        valuteList . size

    /**
     * `fun onActivityDestroy ()` - обнуляет данные дабы избежать утечки памяти
     */
    fun onActivityDestroy () {
        cbService.stopService()
        compositeDisposable.clear()
    }
}