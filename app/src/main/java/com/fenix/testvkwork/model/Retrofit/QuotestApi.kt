package com.fenix.testvkwork.model.Retrofit

import com.fenix.testvkwork.model.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuotestApi {
    @GET("/products")
    suspend fun getProducts(@Query("skip")skip:Int, @Query("limit")limit:Int): Products

    @GET("/products/category/{category}")
    suspend fun getProducts(@Path("category")category:String, @Query("skip")skip:Int, @Query("limit")limit:Int): Products


    @GET("/products/categories")
    suspend fun getFilters():ArrayList<String>

    @GET("/products/search")
    suspend fun getSearchProducts(@Query("q")query:String, @Query("skip")skip:Int,@Query("limit")limit: Int): Products
}