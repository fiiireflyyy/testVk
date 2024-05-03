package com.fenix.testvkwork.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fenix.testvkwork.MainViewModel
import com.fenix.testvkwork.databinding.FragmentMainBinding
import com.fenix.testvkwork.view.ProductsAdapter

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()
    private var _binding:FragmentMainBinding?=null
    private val mBinding get()=_binding!!
    private lateinit var productsAdapter:ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMainBinding.inflate(inflater,container,false)

        viewModel.testDownLoad(0,20)

        productsAdapter= ProductsAdapter()
        mBinding.productRecycler.layoutManager=GridLayoutManager(context,2)
        viewModel.getProductsLive().observe(
            viewLifecycleOwner,
        ){
            arr->productsAdapter.productList=arr
        }
        mBinding.productRecycler.adapter=productsAdapter

        return mBinding.root
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}