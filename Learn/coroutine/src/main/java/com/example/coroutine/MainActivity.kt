package com.example.coroutine

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var job: Job? = null
    var job1: Job? = null
    var tvContent: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * GlobalScope是生命周期是process级别的，即使Activity或者Fragment已经被销毁，协程仍然在执行。所以需要绑定生命周期
         */
//        job1 = GlobalScope.launch(Dispatchers.Main) {
//            tvContent?.text = loadData()
//        }
        dataBinding()
    }

    private fun dataBinding() {
        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java);
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
        val recyclerView = findViewById<RecyclerView>(R.id.lv)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun reload() {
        job1 = GlobalScope.launch(Dispatchers.Main) {
            tvContent?.text = loadData()
        }
    }

    /**
     * suspend: 耗时，数据库网络请求，大量图片美化裁剪运算,大量json数据解析，File操作，大量数组排序处理，delay操作
     */
    suspend fun loadData(): String {
        var response = " "
        withContext(Dispatchers.IO) {
            delay(3000)
            for (i in 0..100000000) {

            }
            response = "response"
        }
        return response
    }

    fun runBlocking() = kotlinx.coroutines.runBlocking {
        repeat(5) {
            Log.i("minfo", "协程执行$it,当前线程id:${Thread.currentThread().id}")
            delay(1000)
        }
    }

    fun click(view: View) {
        runCoroutine()
    }

    fun runCoroutine() {
        Log.i("minfo", "协程开始")
        job = CoroutineScope(Dispatchers.Default).launch {
            delay(4000)
            Log.i("minfo", "协程内部")
        }
        Log.i("minfo", "协程下面")
    }

    fun cancel(view: View) {
        Log.i("minfo", "取消协程")
        job?.cancel()
        job1?.cancel()
    }

//    public fun CoroutineScope.launch(
//        context: CoroutineContext = EmptyCoroutineContext,
//        start: CoroutineStart = CoroutineStart.DEFAULT,
//        block: suspend CoroutineScope.() -> Unit
//    ): Job {
//        val newContext = newCoroutineContext(context)
//        val coroutine = if (start.isLazy)
//            LazyStandaloneCoroutine(newContext, block) else
//            StandaloneCoroutine(newContext, active = true)
//        coroutine.start(start, coroutine, block)
//        return coroutine
//    }
}