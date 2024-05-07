package com.fenix.testvkwork.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fenix.testvkwork.R
import com.fenix.testvkwork.databinding.FragmentMainBinding
import com.fenix.testvkwork.model.WhatDownLoad
import com.fenix.testvkwork.view.FilterAdapter
import com.fenix.testvkwork.view.ProductsAdapter
import com.fenix.testvkwork.viewModel.MainViewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()
    private var _binding:FragmentMainBinding?=null
    private val mBinding get()=_binding!!
    private lateinit var productsAdapter:ProductsAdapter
    private lateinit var filtersAdapter:FilterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMainBinding.inflate(inflater,container,false)


        filtersAdapter= FilterAdapter(viewModel)
        mBinding.filtersRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        viewModel.getFiltersList().observe(
            viewLifecycleOwner,
        ){
            arrf->filtersAdapter.filterList=arrf
        }

        mBinding.filtersRecycler.adapter=filtersAdapter


        productsAdapter= ProductsAdapter(viewModel::setPosChoice,::navigateProduct)
        mBinding.productRecycler.layoutManager=GridLayoutManager(context,2)

        viewModel.getProductsLive().observe(
            viewLifecycleOwner,
        ){
            arr->productsAdapter.productList=arr
        }

        mBinding.productRecycler.adapter=productsAdapter


        mBinding.productRecycler.addOnScrollListener(
            object :RecyclerView.OnScrollListener()  {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val layoutManager = recyclerView.layoutManager as GridLayoutManager
                        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                        Log.d("RRR", "позиция $lastVisibleItemPosition")

                        if (lastVisibleItemPosition >= viewModel.getLastPos()) {
                            when(viewModel.getScrollDownLoad()){
                                WhatDownLoad.MAIN->viewModel.testDownLoad(true)
                                WhatDownLoad.CATEGORY->viewModel.downLoadCategory(true)
                                WhatDownLoad.SEARCH->viewModel.downLoadSearch(true)
                            }
                            Log.d("RRR", "ПРОКРУТИЛОСЬ")
                        }
                    }
                }
            }
        )



        viewModel.getCurrentCategoryLive().observe(
            viewLifecycleOwner,
        ){
            category->mBinding.currentFilter.text=category
        }



        viewModel.getShowBtnCancelLive().observe(
            viewLifecycleOwner,
        ){
            flag->
            if (flag) {
                mBinding.deleteFilterBtn.visibility=View.VISIBLE
            }
            else{
                mBinding.deleteFilterBtn.visibility=View.GONE
            }
        }



        viewModel.getErrorStateLive().observe(
            viewLifecycleOwner,
        ){
            state->
            if(state){
                mBinding.errorLay.visibility=View.VISIBLE
                mBinding.productRecycler.visibility=View.GONE
            }else{
                mBinding.productRecycler.visibility=View.VISIBLE
                mBinding.errorLay.visibility=View.GONE
            }
        }



        viewModel.getToastErrorLive().observe(
            viewLifecycleOwner,
        ){
            flag->
            if(flag)
                Toast.makeText(context,"Отсутствует подключение к интернету",Toast.LENGTH_SHORT).show()
        }



        mBinding.updateBtn.setOnClickListener {
            viewModel.downLoadFilters()
            viewModel.testDownLoad(false)
        }



        mBinding.deleteFilterBtn.setOnClickListener {
            viewModel.testDownLoad(false)
            viewModel.setShowBtnCancel(false)
            viewModel.currentPos=null
            viewModel.setCurrentCategory("Выберите категорию")
            filtersAdapter.reDrawOut()
            viewModel.setScrollDownLoad(WhatDownLoad.MAIN)
        }


        mBinding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("RRR","ЗАПРОС $query")
                    if(query!=null) {
                        filtersAdapter.reDrawOut()
                        viewModel.setSearch(query)
                        viewModel.downLoadSearch(false)
                        viewModel.setScrollDownLoad(WhatDownLoad.SEARCH)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText=="" && viewModel.getSearch()!="" && viewModel.getSearch()!=null && viewModel.currentPos==null){
                        mBinding.productRecycler.smoothScrollToPosition(0)
                        viewModel.setSearch("")
                        filtersAdapter.reDrawOut()
                        viewModel.testDownLoad(false)
                        viewModel.setScrollDownLoad(WhatDownLoad.MAIN)
                    }
                    return true
                }

            }
        )

        return mBinding.root
    }


    fun navigateProduct(){
        findNavController().navigate(R.id.action_mainFragment_to_productFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}