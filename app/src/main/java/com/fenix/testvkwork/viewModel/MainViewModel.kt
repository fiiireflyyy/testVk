package com.fenix.testvkwork.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fenix.testvkwork.model.Product
import com.fenix.testvkwork.model.Repository

class MainViewModel : ViewModel() {

    private val repository=Repository()

    private var skip=0
    private val limit=20

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