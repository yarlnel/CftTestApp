package com.example.cfttestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cfttestapp.R
import com.example.cfttestapp.api.CentralBankService
import com.example.cfttestapp.appComponent
import com.example.cfttestapp.db.MainDao
import com.example.cfttestapp.pojo.ResponseFromCB
import com.example.cfttestapp.pojo.Valute
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * `MainActivity` - Главный экран приложения
 */
class MainActivity : AppCompatActivity() {
    @Inject lateinit var centralBankService : CentralBankService
    @Inject lateinit var mainDao: MainDao
    @Inject lateinit var valuteListAdapter: ValuteRecyclerViewAdapter
    @Inject lateinit var linearLayoutManager: LinearLayoutManager

    private val valuteRecyclerView: RecyclerView by lazy {
        findViewById(R.id.valuteRecyclerView)
    }

    private val rubleSumEditText: EditText by lazy {
        findViewById(R.id.moneySumEditText)
    }

    private val rubleSumBundleKey = "ruble_sum_et"

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent . inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valuteRecyclerView . apply {
            layoutManager = linearLayoutManager
            adapter = valuteListAdapter
        }

        rubleSumEditText . apply {
            doOnTextChanged { text, _, _, _ ->
                if (text!!.isNotEmpty())
                    valuteListAdapter.setRubleSum(text.toString().toDouble())
                else
                    valuteListAdapter.setRubleSum(0.0)
            }
        }
    }

    override fun onDestroy() {
        valuteListAdapter . onActivityDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putString(rubleSumBundleKey, rubleSumEditText . text . toString())
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState . getString(rubleSumBundleKey) ?. let { text ->
            rubleSumEditText . setText(text)
        }
    }
}