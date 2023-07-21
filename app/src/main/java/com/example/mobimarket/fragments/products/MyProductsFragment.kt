package com.example.mobimarket.fragments.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobimarket.fragments.addProducts.AddProductViewModel
import com.example.namespace.databinding.MyProductsFragmentBinding

class MyProductsFragment : Fragment() {
    private lateinit var binding: MyProductsFragmentBinding
    private lateinit var viewModel: AddProductViewModel
    private val productAdapter: ProductAdapter by lazy { ProductAdapter(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyProductsFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(AddProductViewModel::class.java)

        val gridLayoutManager = GridLayoutManager(requireContext(), 2) // Span count 2 for two items in one row
        binding.productRecyclerView.layoutManager = gridLayoutManager
        binding.productRecyclerView.adapter = productAdapter

        viewModel.productList.observe(viewLifecycleOwner, { productList ->
            productAdapter.updateProductList(productList)
        })

        productAdapter.setOnItemClickListener { product ->

            val action = MyProductsFragmentDirections.actionMyProductsFragment2ToItemViewFragment(product)
            findNavController().navigate(action)
        }

        return binding.root
    }
}
