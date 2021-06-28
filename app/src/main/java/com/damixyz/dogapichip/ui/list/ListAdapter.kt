package com.damixyz.dogapichip.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.damixyz.dogapichip.R
import com.damixyz.dogapichip.databinding.ListDogBreedBinding

class ListAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val list: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent = parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = list[position], listener = listener)
    }

    override fun getItemCount(): Int = list.size

    fun setData(data: List<String>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListDogBreedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, listener: OnClickListener) {
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
            binding.breedName = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListDogBreedBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.list_dog_breed, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


interface OnClickListener {
    fun onItemClick(breedName: String)
}
