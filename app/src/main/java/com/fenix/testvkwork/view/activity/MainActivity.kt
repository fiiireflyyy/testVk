package com.fenix.testvkwork.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fenix.testvkwork.R
import com.fenix.testvkwork.model.Product
import com.fenix.testvkwork.model.QuotestApi
import com.fenix.testvkwork.model.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val quotestApi=RetrofitHelper.getInstance().create(QuotestApi::class.java)
        GlobalScope.launch {
            val products=quotestApi.getProducts(20,20)
            if (products!=null)
                Log.d("RRR",products.products[0].toString())
        }
    }
}