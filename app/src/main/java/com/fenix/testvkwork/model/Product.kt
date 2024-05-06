package com.fenix.testvkwork.model

class Product(
    val id: Long,
    val title:String,
    val description:String,
    val price:Double,
    val discountPercentage: Double,
    val rating:Double,
    val stock:Long,
    val brand:String,
    val category:String,
    val thumbnail:String,
    val images:ArrayList<String>)
{
    override fun toString(): String {
        return "Product(id=$id, name=$title, price=$price)"
    }
}