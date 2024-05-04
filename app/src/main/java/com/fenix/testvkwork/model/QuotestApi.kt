package com.fenix.testvkwork.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuotestApi {
    @GET("/products")
    suspend fun getProducts(@Query("skip")skip:Int, @Query("limit")limit:Int):Products

    @GET("/products/category/{category}")
    suspend fun getProducts(@Path("category")category:String):Products


    @GET("/products/categories")
    suspend fun getFilters():ArrayList<String>

}