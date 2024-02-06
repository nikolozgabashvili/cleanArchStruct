package com.example.cleanapistruct.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanapistruct.common.extension.loadBackground
import com.example.cleanapistruct.databinding.CardItemBinding
import com.example.cleanapistruct.domain.model.Color

class ColorAdapter(private val list: List<Color>) :
    RecyclerView.Adapter<ColorAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    var onItemClick: ((Color) -> Unit)? = null
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            title.text = item.title
            username.text = item.userName
            item.imageUrl?.let {
                llInnerLayout.loadBackground(item.imageUrl)
            }
            root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}