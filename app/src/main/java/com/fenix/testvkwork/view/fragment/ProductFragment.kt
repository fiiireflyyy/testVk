package com.fenix.testvkwork.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.fenix.testvkwork.R
import com.fenix.testvkwork.databinding.FragmentProductBinding
import com.fenix.testvkwork.model.Product
import com.fenix.testvkwork.view.ImageAdapter
import com.fenix.testvkwork.viewModel.MainViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy

class ProductFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels<MainViewModel>()

    private var _binding:FragmentProductBinding?=null
    private val mBinding get()=_binding!!

    private lateinit var productImageAdapter:ImageAdapter
    private lateinit var product: Product
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentProductBinding.inflate(inflater,container,false)

        product=viewModel.getChoiceProduct()
        productImageAdapter=ImageAdapter(product.images)
        mBinding.imageRecycler.layoutManager=CarouselLayoutManager(HeroCarouselStrategy())
        mBinding.imageRecycler.adapter=productImageAdapter

        return mBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setForScroll("unScroll")
        _binding=null
    }
}