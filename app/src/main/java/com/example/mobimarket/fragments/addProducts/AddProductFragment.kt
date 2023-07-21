package com.example.mobimarket.fragments.addProducts

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobimarket.data.Product
import com.example.namespace.databinding.AddProductFragmentBinding

class AddProductFragment : Fragment() {
    private lateinit var binding: AddProductFragmentBinding
    private val imageList: ArrayList<Uri> = ArrayList()
    private lateinit var viewModel: AddProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddProductFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AddProductViewModel::class.java)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val imageListAdapter = ImageListAdapter(imageList)
        binding.imageListRecyclerView.layoutManager = layoutManager
        binding.imageListRecyclerView.adapter = imageListAdapter
        binding.acceptAddButton.setOnClickListener {

            val productName = binding.addProductName.text.toString()
            val productPrice = binding.addProductPrice.text.toString()
            val productShortDesc = binding.addProductShortDescription.text.toString()
            val productFullDesc = binding.addProductFullDescription.text.toString()
            val productImages = imageList

            val product = Product(id, productName, productPrice, productShortDesc, productFullDesc, productImages)


            viewModel.addProduct(product)

            findNavController().popBackStack()
        }


        binding.cancelAddButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.productImageBack.setOnClickListener {
            openImagePicker()
        }

        return binding.root
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGES_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGES_REQUEST && resultCode == RESULT_OK) {
            if (data?.clipData != null) {
                val clipData = data.clipData
                for (i in 0 until clipData!!.itemCount) {
                    val imageUri = clipData.getItemAt(i).uri
                    imageList.add(imageUri)
                }
            } else if (data?.data != null) {
                val imageUri = data.data
                if (imageUri != null) {
                    imageList.add(imageUri)
                }
            }
            binding.imageListRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    companion object {
        private const val PICK_IMAGES_REQUEST = 1
    }
}
