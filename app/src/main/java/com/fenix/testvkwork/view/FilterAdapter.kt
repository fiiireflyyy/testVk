package com.fenix.testvkwork.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fenix.testvkwork.R
import com.fenix.testvkwork.databinding.ItemFilterBinding
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


    class ViewHolder(item: View):RecyclerView.ViewHolder(item){

        private var _binding : ItemFilterBinding
        val mBinding get()=_binding

        init {
            _binding=ItemFilterBinding.bind(item)
        }
        private val filterText=mBinding.filterItem
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
//        holder.mBinding.filterItem.setOnClickListener {
//            viewModel.downLoadCategory(filterList[position])
//        }
        holder.mBinding.filterItem.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked){
                viewModel.downLoadCategory(filterList[position])
                viewModel.setScrollDownLoad(false)
            }
            else{
                viewModel.testDownLoad()
                viewModel.setScrollDownLoad(true)
            }
        }
    }


}