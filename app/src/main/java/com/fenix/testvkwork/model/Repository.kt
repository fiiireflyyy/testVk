package com.fenix.testvkwork.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository {

    private val productsLiveData:MutableLiveData<ArrayList<Product>> by lazy { MutableLiveData<ArrayList<Product>>() }
    private val productsL=ArrayList<Product>()
    private val filtersLiveData:MutableLiveData<ArrayList<String>> by lazy {MutableLiveData<ArrayList<String>>()}
    val quotestApi=RetrofitHelper.getInstance().create(QuotestApi::class.java)


    fun testDownLoad(skip:Int, limit:Int){
        GlobalScope.launch {
            val products=quotestApi.getProducts(skip,limit)
            productsL+=products.products
            productsLiveData.postValue(productsL)
            if (products!=null)
                Log.d("RRR",productsL[productsL.size-1].toString())
        }
    }

    fun downLoadFilters(){
        GlobalScope.launch {
            val filters=quotestApi.getFilters()
            if(filters!=null)
                filtersLiveData.postValue(filters)
                Log.d("RRR","Фильтры ${filters[0]}   ${filters[10]}")
        }
    }

    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return productsLiveData
    }

    fun getFiltersList(): MutableLiveData<ArrayList<String>> {
        return filtersLiveData
    }

}