package com.fenix.testvkwork.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fenix.testvkwork.R
import com.fenix.testvkwork.databinding.ItemImageBinding
import com.fenix.testvkwork.viewModel.MainViewModel

class ImageAdapter(
    private val imageList:ArrayList<String>
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {




    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        private var _binding: ItemImageBinding
        val mBinding get()=_binding
        init {
            _binding= ItemImageBinding.bind(item)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_image, parent, false))
    }

    override fun getItemCount()=imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(imageList[position])
            .placeholder(R.drawable.white_bg)
            .into(holder.mBinding.imageView)
    }


}