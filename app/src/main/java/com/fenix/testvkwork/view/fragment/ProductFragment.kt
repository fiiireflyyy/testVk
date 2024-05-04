package com.fenix.testvkwork.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.fenix.testvkwork.R
import com.fenix.testvkwork.databinding.FragmentProductBinding
import com.fenix.testvkwork.viewModel.MainViewModel

class ProductFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels<MainViewModel>()

    private var _binding:FragmentProductBinding?=null
    private val mBinding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentProductBinding.inflate(inflater,container,false)



        return mBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}