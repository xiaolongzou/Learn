package com.example.coroutine

import android.util.Log
import android.widget.TextView
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.BR
import java.util.*

class MyViewModel: ViewModel() {
    var items = ObservableArrayList<DataBean>()

    val itemBinding by lazy {
        ItemBinding.of<DataBean>(BR.item, R.layout.item).bindExtra(BR.viewModel, this)
    }

//    fun countDown() {
//        viewModelScope.launch {
//            flow<Int> {
//                (60 downTo 1).forEach {
//                    delay(1000)
//                    emit(it)
//                }
//            }.flowOn(Dispatchers.Default)
//                .onStart {
//                    //倒计时开始
//                }
//                .onCompletion {
//                    //倒计时开始
//                }
//                .collect {
//                    //倒计时进程中
//                    count.value = count.value?.minus(1)
//                    tvContent?.text = count.value.toString()
//                }
//        }
//
//    }

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
                try {
                    val dataBean = RetrofitServiceManager.create(APIService::class.java)
                        .getFindData()
                        .dataConvert()
                    Log.i("data", dataBean.toString())
                    items.addAll(dataBean)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

        }
    }
}


