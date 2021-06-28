package com.damixyz.dogapichip.ui.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.damixyz.dogapichip.R
import com.damixyz.dogapichip.databinding.ListBreedImagesBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val list: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent = parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(data: List<String>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListBreedImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.url = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListBreedImagesBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.list_breed_images, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}