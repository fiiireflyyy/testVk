package com.fenix.testvkwork.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fenix.testvkwork.viewModel.MainViewModel
import com.fenix.testvkwork.databinding.FragmentMainBinding
import com.fenix.testvkwork.view.FilterAdapter
import com.fenix.testvkwork.view.ProductsAdapter

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

        viewModel.downLoadFilters()
        viewModel.testDownLoad()

        filtersAdapter= FilterAdapter(viewModel)
        mBinding.filtersRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        viewModel.getFiltersList().observe(
            viewLifecycleOwner,
        ){
            arrf->filtersAdapter.filterList=arrf
        }
        mBinding.filtersRecycler.adapter=filtersAdapter


        productsAdapter= ProductsAdapter()
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
                            Log.d("RRR", "ПРОКРУТИЛОСЬ")
                            viewModel.testDownLoad()
                        }
                    }
                }
            }
        )



        return mBinding.root
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}