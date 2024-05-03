package com.fenix.testvkwork.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository {

    private val productsLiveData:MutableLiveData<ArrayList<Product>> by lazy { MutableLiveData<ArrayList<Product>>() }
    fun testDownLoad(skip:Int, limit:Int){
        val quotestApi=RetrofitHelper.getInstance().create(QuotestApi::class.java)
        GlobalScope.launch {
            val products=quotestApi.getProducts(skip,limit)
            productsLiveData.postValue(products.products)
            if (products!=null)
                Log.d("RRR",products.products[0].toString())
        }
    }

    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return productsLiveData
    }

}