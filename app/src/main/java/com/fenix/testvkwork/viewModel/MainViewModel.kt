package com.fenix.testvkwork.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fenix.testvkwork.model.Product
import com.fenix.testvkwork.model.Repository
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository=Repository()


    private var scrollDownLoad=true
    private val currentCategory:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val showBtnCancel:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

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
    fun getScrollDownLoad(): Boolean {
        return scrollDownLoad
    }
    fun setScrollDownLoad(bool:Boolean){
        scrollDownLoad=bool
    }

    fun getLastPos(): Int {
        return repository.getLastPos()
    }
    fun testDownLoad(){
        viewModelScope.launch {
            repository.testDownLoad()
        }
    }

    fun downLoadFilters(){
        viewModelScope.launch {
            repository.downLoadFilters()
        }
    }

    fun downLoadCategory(category:String){
        viewModelScope.launch {
            repository.downLoadCategory(category)
        }
    }

    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return repository.getProductsLive()
    }

    fun getFiltersList(): MutableLiveData<ArrayList<String>> {
        return repository.getFiltersList()
    }



}