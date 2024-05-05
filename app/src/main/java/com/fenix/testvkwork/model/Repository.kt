package com.fenix.testvkwork.model

import android.util.Log
import androidx.lifecycle.MutableLiveData

class Repository {

    private var skip=0
    private val limit=20

    private val productsLiveData:MutableLiveData<ArrayList<Product>> by lazy { MutableLiveData<ArrayList<Product>>() }
    private var productsL=ArrayList<Product>()
    private val filtersLiveData:MutableLiveData<ArrayList<String>> by lazy {MutableLiveData<ArrayList<String>>()}
    private val quotestApi=RetrofitHelper.getInstance().create(QuotestApi::class.java)
    private val errorState:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>()}
    private val toastError:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    fun getLastPos():Int{
        return skip-10
    }
    fun getErrorStateLive(): MutableLiveData<Boolean> {
        return errorState
    }

    fun getToastErrorLive(): MutableLiveData<Boolean> {
        return toastError
    }
    suspend fun testDownLoad(){
        try {
            skip+=20
            val products=quotestApi.getProducts(skip-20,limit)
            if(skip-20!=0) {
                productsL+=products.products
            } else productsL=products.products
            productsLiveData.postValue(productsL)
            if (products!=null)
                Log.d("RRR",productsL[productsL.size-1].toString())
            Log.d("RRR","Скип $skip")
        } catch (e: Exception){
            skip-=20
            toastError.postValue(true)
            Log.d("RRR","Ошибка поймана загрузка")
        }

    }

    suspend fun downLoadFilters(){
        try {
            errorState.postValue(false)
            val filters=quotestApi.getFilters()
            filtersLiveData.postValue(filters)
        } catch (e:Exception){
            Log.d("RRR","ОШибка категорий")
            errorState.postValue(true)
        }

    }

    suspend fun downLoadCategory(category:String){
        try {
            Log.d("RRR",category)
            val products=quotestApi.getProducts(category)
            productsL=products.products
            productsLiveData.postValue(productsL)
            skip=0
        } catch (e:Exception){
            toastError.postValue(true)
        }

    }

    suspend fun downLoadSearch(search:String){
        try {
            Log.d("RRR",search)
            val products=quotestApi.getSearchProducts(search)
            productsL=products.products

            productsLiveData.postValue(productsL)
            skip=0
        }catch (e:Exception){
            Log.d("RRR","ОШИБКА $e")
            toastError.postValue(true)
        }
    }
    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return productsLiveData
    }

    fun getFiltersList(): MutableLiveData<ArrayList<String>> {
        return filtersLiveData
    }
}