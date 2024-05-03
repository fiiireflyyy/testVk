package com.fenix.testvkwork.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fenix.testvkwork.R
import com.fenix.testvkwork.databinding.ItemProductBinding
import com.fenix.testvkwork.model.Product

class ProductsAdapter:RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var productList= listOf<Product>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        private var _binding:ItemProductBinding
        val mBinding get()=_binding
        init {
            _binding= ItemProductBinding.bind(item)
        }

        private val title=mBinding.titleText
        private val description=mBinding.description
        private val price=mBinding.price
        private val stock=mBinding.remainCount

        fun onBind(items: Product){
            title.text=items.title
            description.text=items.description
            price.text=items.price
            stock.text= items.stock.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_product,parent,false))
    }

    override fun getItemCount()=productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(productList[position])
    }

}