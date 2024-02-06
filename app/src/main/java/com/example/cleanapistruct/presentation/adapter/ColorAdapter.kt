package com.example.cleanapistruct.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanapistruct.databinding.ColorItemBinding
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.extension.loadImage

class ColorAdapter(private var colorsList: List<Color>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    var onItemClickListener: ((Color) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ColorItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val item = colorsList[position]
            binding.title.text = item.title
            binding.username.text = item.userName
            item.imageUrl?.let {
                binding.root.loadImage(item.imageUrl)
            }

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }

        }
    }

    override fun getItemCount() = colorsList.size
    class ViewHolder(val binding: ColorItemBinding) : RecyclerView.ViewHolder(binding.root)


}