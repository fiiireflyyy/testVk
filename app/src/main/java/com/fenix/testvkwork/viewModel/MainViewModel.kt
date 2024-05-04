package com.fenix.testvkwork.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fenix.testvkwork.model.Product
import com.fenix.testvkwork.model.Repository
import com.google.android.material.chip.Chip

class MainViewModel : ViewModel() {

    private val repository=Repository()

    private var skip=0
    private val limit=20
    private var scrollDownLoad=true
    private val currentCategory:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val showBtnCancel:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


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
        return skip-10
    }
    fun testDownLoad(){
        repository.testDownLoad(skip, limit)
        skip+=20
    }

    fun downLoadFilters(){
        repository.downLoadFilters()
    }

    fun downLoadCategory(category:String){
        skip=0
        repository.downLoadCategory(category, skip, limit)
    }

    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return repository.getProductsLive()
    }

    fun getFiltersList(): MutableLiveData<ArrayList<String>> {
        return repository.getFiltersList()
    }



}