package com.fenix.testvkwork.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fenix.testvkwork.R
import com.fenix.testvkwork.databinding.ItemFilterBinding
import com.fenix.testvkwork.model.WhatDownLoad
import com.fenix.testvkwork.viewModel.MainViewModel

class FilterAdapter(
    val viewModel:MainViewModel
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    var filterList= listOf<String>()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field=value
            notifyDataSetChanged()
        }

    private var currentCategoryHolder:ViewHolder?=null
    private var currentPosition:Int?=null
    init {
        Log.d("RRR","ADAPTER")
        currentPosition=viewModel.currentPos
    }


    class ViewHolder(item: View):RecyclerView.ViewHolder(item){

        private var _binding : ItemFilterBinding
        val mBinding get()=_binding

        init {
            _binding=ItemFilterBinding.bind(item)
        }
        private val filterText=mBinding.chipText
        fun onBind(items:String){
            filterText.text=items
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_filter, parent, false))
    }

    override fun getItemCount()=filterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(filterList[position])

        if (currentPosition==position){
            currentCategoryHolder=holder
            holder.mBinding.chipLayout.setBackgroundResource(R.drawable.filter_on_bg)
        }
        else{
            holder.mBinding.chipLayout.setBackgroundResource(R.drawable.filter_bg)
        }


        holder.mBinding.chipLayout.setOnClickListener {
            if ( currentPosition==position){
                viewModel.setScrollDownLoad(WhatDownLoad.MAIN)
                viewModel.testDownLoad(false)
                viewModel.setShowBtnCancel(false)
                viewModel.setCurrentCategory("Выберите категорию")
                holder.mBinding.chipLayout.setBackgroundResource(R.drawable.filter_bg)
                viewModel.currentPos=null
                currentPosition=null
                currentCategoryHolder=null
            } else {
                viewModel.category=filterList[position]
                viewModel.setCurrentCategory(filterList[position])
                viewModel.setScrollDownLoad(WhatDownLoad.CATEGORY)
                viewModel.downLoadCategory(false)
                viewModel.setShowBtnCancel(true)
                reDrawOldButton(holder,position)
                viewModel.currentPos=position
                holder.mBinding.chipLayout.setBackgroundResource(R.drawable.filter_on_bg)
            }

        }
    }

    fun reDrawOut(){
        currentPosition=null
        viewModel.setShowBtnCancel(false)
        viewModel.currentPos=null
        viewModel.setCurrentCategory("Выберите категорию")
        if(currentCategoryHolder!=null)
            currentCategoryHolder!!.mBinding.chipLayout.setBackgroundResource(R.drawable.filter_bg)
    }
    fun reDrawOldButton(holder: ViewHolder,position:Int){
        if (currentCategoryHolder != null && currentPosition!=position){
            currentCategoryHolder!!.mBinding.chipLayout.setBackgroundResource(R.drawable.filter_bg)
        }
        currentCategoryHolder = holder
        currentPosition=position
    }




}