package com.example.mobimarket.fragments.addProducts
import android.net.Uri
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.namespace.R
import com.example.namespace.databinding.ItemImageBinding

class ImageListAdapter(private val imageList: List<Uri>) : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUri = imageList[position]
        holder.bindImage(imageUri)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindImage(imageUri: Uri) {
            Glide.with(binding.imageView.context)
                .load(imageUri)
                .placeholder(R.drawable.image_add)
                .error(R.drawable.arrowback)
                .into(binding.imageView)
        }
    }
}
