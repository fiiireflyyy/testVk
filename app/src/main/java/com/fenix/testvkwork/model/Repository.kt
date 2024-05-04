package com.fenix.testvkwork.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository {

    private val productsLiveData:MutableLiveData<ArrayList<Product>> by lazy { MutableLiveData<ArrayList<Product>>() }
    private var productsL=ArrayList<Product>()
    private val filtersLiveData:MutableLiveData<ArrayList<String>> by lazy {MutableLiveData<ArrayList<String>>()}
    private val quotestApi=RetrofitHelper.getInstance().create(QuotestApi::class.java)


    suspend fun testDownLoad(skip:Int, limit:Int){
            val products=quotestApi.getProducts(skip,limit)
            if(skip!=0) {
                productsL+=products.products
            } else productsL=products.products
            productsLiveData.postValue(productsL)
            if (products!=null)
                Log.d("RRR",productsL[productsL.size-1].toString())
    }

    suspend fun downLoadFilters(){
            val filters=quotestApi.getFilters()
            filtersLiveData.postValue(filters)
    }

    suspend fun downLoadCategory(category:String,skip:Int, limit:Int){
            Log.d("RRR",category)
            val products=quotestApi.getProducts(category)
            productsL=products.products
            productsLiveData.postValue(productsL)
            if (products!=null)
                Log.d("RRR",productsL[productsL.size-1].toString())
    }
    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return productsLiveData
    }

    fun getFiltersList(): MutableLiveData<ArrayList<String>> {
        return filtersLiveData
    }
}