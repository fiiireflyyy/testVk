package com.fenix.testvkwork

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

    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return repository.getProductsLive()
    }

}