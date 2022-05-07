package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var job: Job? = null
    var job1: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tvContent  = findViewById<TextView>(R.id.tv)
        job1 = GlobalScope.launch(Dispatchers.Main) {
            tvContent.text = loadData()
        }
    }

    /**
     * suspend: 耗时，数据库网络请求，大量图片美化裁剪运算
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