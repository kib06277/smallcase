package com.funshow.okhttp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // 建立OkHttpClient
    var client: OkHttpClient = OkHttpClient().newBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUrl("https://opendata.cwa.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=rdec-key-123-45678-011121314")
        postUrl("http://122.146.250.130/SGNR/Price/pricelobbyData")
    }

    fun getUrl(url: String?) {
        // 建立Request，設置連線資訊
        val request: Request = Builder()
            .url(url)
            .build()

        // 建立Call
        val call: Call = client.newCall(request)

        // 執行Call連線到網址
        call.enqueue(object : Callback {
            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                // 連線成功，自response取得連線結果
                val result: String = response.body().string()
                Log.i("OkHttp", "get = $result")
            }

            override fun onFailure(call: Call?, e: IOException) {
                // 連線失敗
                Log.i("OkHttp", "get error = $e")
            }
        })
    }

    fun postUrl(url: String?) {
        // FormBody放要傳的參數和值
        val formBody = FormBody.Builder()
            .add("market", "1")
            .add("saler_code", "L999")
            .add("goods_code", "")
            .add("goods_fv", "")
            .build()

        // 建立Request，設置連線資訊
        val request: Request = Builder()
            .url(url)
            .post(formBody) // 使用post連線
            .build()

        // 建立Call
        val call: Call = client.newCall(request)

        // 執行Call連線到網址
        call.enqueue(object : Callback {
            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                // 連線成功
                val result: String = response.body().string()
                Log.i("OkHttp", "post = $result")
            }

            override fun onFailure(call: Call?, e: IOException) {
                // 連線失敗
                Log.i("OkHttp", "post error = $e")
            }
        })
    }
}