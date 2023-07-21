package com.example.mobimarket.fragments.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobimarket.data.Product

import com.example.namespace.R
import com.example.namespace.databinding.ItemProductBinding

class ProductAdapter(private var productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var onItemClick: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bindProduct(product)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    private var itemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        itemClickListener = listener
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindProduct(product: Product) {
            binding.productNameTextView.text = product.name
            binding.productPriceTextView.text = product.price

            if (product.imageList.isNotEmpty()) {
                val firstImage = product.imageList[0]
                Glide.with(binding.productImageView.context)
                    .load(firstImage)
                    .placeholder(R.drawable.image_add)
                    .error(R.drawable.arrowback)
                    .into(binding.productImageView)
            }
            binding.root.setOnClickListener {
                itemClickListener?.invoke(product)
            }

        }
    }

    fun updateProductList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }
}
