package com.fenix.testvkwork

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fenix.testvkwork.model.Product
import com.fenix.testvkwork.model.Repository

class MainViewModel : ViewModel() {

    private val repository=Repository()
    fun testDownLoad(skip:Int, limit:Int){
        repository.testDownLoad(skip, limit)
    }

    fun getProductsLive(): MutableLiveData<ArrayList<Product>> {
        return repository.getProductsLive()
    }

}