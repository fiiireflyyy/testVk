package com.fenix.testvkwork.view.fragment

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        mBinding.categoryText.text="Home > ${product.category}"
        mBinding.productCode.text=product.id.toString()
        mBinding.productName.text=product.title
        mBinding.productRating.text=("%.2f".format(product.rating))
        mBinding.productRemain.text=product.stock.toString()
        mBinding.oldPrice.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
        mBinding.oldPrice.text=("%.2f".format(product.price)+"$")
        mBinding.discountPercent.text=("-"+"%.2f".format(product.discountPercentage)+"%")
        mBinding.price.text=("%.2f".format(product.price*(1-product.discountPercentage/100))+" $")
        mBinding.description.text=product.description

        productImageAdapter=ImageAdapter(product.images)
        mBinding.imageRecycler.layoutManager=CarouselLayoutManager(HeroCarouselStrategy())
        mBinding.imageRecycler.adapter=productImageAdapter


        mBinding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return mBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}