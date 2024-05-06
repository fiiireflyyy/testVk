package com.fenix.testvkwork.view

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
        private val rating=mBinding.rating
        fun onBind(items: Product){
            title.text=items.title
            description.text=items.description
            price.text=items.price.toString()
            stock.text= items.stock.toString()
            rating.text=items.rating.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_product,parent,false))
    }

    override fun getItemCount()=productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(productList[position])
        holder.mBinding.price.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
        holder.mBinding.price.apply {
            text = "%.2f".format(productList[position].price )+"$"
        }
        holder.mBinding.afterDiscont.apply {
            text = "%.2f".format(productList[position].price * (1 - productList[position].discountPercentage / 100))+"$"
        }
        holder.mBinding.discountPercent.apply {
            text = "-%.2f".format(productList[position].discountPercentage)+"%"
        }
        Glide.with(holder.itemView)
            .load(productList[position].thumbnail)
            .transform(RoundedCorners(20))
            .placeholder(R.drawable.white_bg)
            .error(R.drawable.circle_bg)
            .into(holder.mBinding.imageProduct)
    }

}