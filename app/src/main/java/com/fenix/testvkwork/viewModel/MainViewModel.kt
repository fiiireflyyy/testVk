package com.fenix.testvkwork.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.testvkwork.model.Product
import com.fenix.testvkwork.model.Repository
import com.fenix.testvkwork.model.WhatDownLoad
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository=Repository()


    private var searchQuery:String?=null
    var category=""
    private val currentCategory:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val showBtnCancel:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun setSearch(search:String){
        searchQuery = search
    }
    fun getErrorStateLive(): MutableLiveData<Boolean> {
        return repository.getErrorStateLive()
    }

    fun getToastErrorLive(): MutableLiveData<Boolean> {
        return repository.getToastErrorLive()
    }
    fun setShowBtnCancel(flag:Boolean){
        showBtnCancel.postValue(flag)
    }
    fun getShowBtnCancelLive(): MutableLiveData<Boolean> {
        return showBtnCancel
    }
    fun getCurrentCategoryLive(): MutableLiveData<String> {
        return currentCategory
    }

    fun setCurrentCategory(category:String){
        currentCategory.postValue(category)
    }
    fun getScrollDownLoad(): WhatDownLoad {
        return repository.getScrollDown()
    }
    fun setScrollDownLoad(type:WhatDownLoad){
        repository.setScrollDown(type)
    }

    fun getLastPos(): Int {
        return repository.getLastPos()
    }
    fun testDownLoad(afterScroll:Boolean){
        viewModelScope.launch {
            repository.testDownLoad(afterScroll)
        }
    }

    fun downLoadFilters(){
        viewModelScope.launch {
            repository.downLoadFilters()
        }
    }

    fun downLoadCategory(afterScroll:Boolean){
        viewModelScope.launch {
            repository.downLoadCategory(category, afterScroll)
        }

    }

    fun downLoadSearch(afterScroll:Boolean){
        viewModelScope.launch {
            repository.downLoadSearch(searchQuery!!, afterScroll)
        }
    }

    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return repository.getProductsLive()
    }

    fun getFiltersList(): MutableLiveData<ArrayList<String>> {
        return repository.getFiltersList()
    }



}