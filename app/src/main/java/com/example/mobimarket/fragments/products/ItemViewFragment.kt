package com.example.mobimarket.fragments.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mobimarket.fragments.addProducts.AddProductViewModel
import com.example.namespace.R
import com.example.namespace.databinding.ItemViewFragmentBinding
class ItemViewFragment : Fragment() {
    lateinit var binding:ItemViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemViewFragmentBinding.inflate(inflater, container, false)


        val productId = arguments?.getInt("productId")

        val viewModel = ViewModelProvider(requireActivity()).get(AddProductViewModel::class.java)
        val product = viewModel.getProductById(productId)

        if (product != null) {
            binding.productName.text = product.name
            binding.productPrice.text = product.price
            binding.productShortDescription.text = product.shortDescription
            binding.productFullDescription.text = product.fullDescription

            if (product.imageList.isNotEmpty()) {
                val firstImage = product.imageList[0]
                Glide.with(requireContext())
                    .load(firstImage)
                    .placeholder(R.drawable.image_add)
                    .error(R.drawable.arrowback)
                    .into(binding.productImage)
            }
        }

        return binding.root
    }
}


