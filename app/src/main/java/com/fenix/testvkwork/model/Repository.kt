package com.fenix.testvkwork.model

import android.util.Log
import androidx.lifecycle.MutableLiveData

class Repository {

    private var skip=0
    private var oldSkip=0
    private val limit=20
    private var scrollDownLoad=WhatDownLoad.MAIN

    private val productsLiveData:MutableLiveData<ArrayList<Product>> by lazy { MutableLiveData<ArrayList<Product>>() }
    private var productsL=ArrayList<Product>()
    private val filtersLiveData:MutableLiveData<ArrayList<String>> by lazy {MutableLiveData<ArrayList<String>>()}
    private val quotestApi=RetrofitHelper.getInstance().create(QuotestApi::class.java)
    private val errorState:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>()}
    private val toastError:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    fun getChoiceProduct(pos:Int): Product {
        return productsL[pos]
    }
    fun getScrollDown(): WhatDownLoad {
        return scrollDownLoad
    }
    fun setScrollDown(type:WhatDownLoad){
        scrollDownLoad=type
    }
    fun getLastPos():Int{
        return skip-10
    }
    fun getErrorStateLive(): MutableLiveData<Boolean> {
        return errorState
    }

    fun getToastErrorLive(): MutableLiveData<Boolean> {
        return toastError
    }
    suspend fun testDownLoad(afterScroll: Boolean){
        try {
            if(!afterScroll){
                oldSkip=skip
                skip=0
                val products=quotestApi.getProducts(skip,limit)
                productsL=products.products
                productsLiveData.postValue(productsL)
            }
            else{
                skip+=20
                val products=quotestApi.getProducts(skip,limit)
                productsL+=products.products
                productsLiveData.postValue(productsL)
            }
        } catch (e: Exception){
            if (afterScroll){
                skip-=20
            }else {
                skip=oldSkip
            }
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

    suspend fun downLoadCategory(category:String, afterScroll:Boolean){
        try {
            Log.d("RRR",category)
            if (!afterScroll){
                oldSkip=skip
                skip=0
                val products=quotestApi.getProducts(category,skip,limit)
                productsL=products.products
                productsLiveData.postValue(productsL)
            }
            else{
                skip+=20
                val products=quotestApi.getProducts(category,skip,limit)
                productsL+=products.products
                productsLiveData.postValue(productsL)
            }
        } catch (e:Exception){
            if (afterScroll){
                skip-=20
            }else {
                skip=oldSkip
            }
            toastError.postValue(true)
        }

    }

    suspend fun downLoadSearch(search:String,afterScroll: Boolean){
        try {
            Log.d("RRR",search)
            if(!afterScroll) {
                oldSkip=skip
                skip=0
                val products=quotestApi.getSearchProducts(search,skip, limit)
                productsL=products.products
                productsLiveData.postValue(productsL)
            }else{
                skip+=20
                val products=quotestApi.getSearchProducts(search,skip, limit)
                productsL+=products.products
                productsLiveData.postValue(productsL)
            }
        }catch (e:Exception){
            if (afterScroll){
                skip-=20
            }else {
                skip=oldSkip
            }
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