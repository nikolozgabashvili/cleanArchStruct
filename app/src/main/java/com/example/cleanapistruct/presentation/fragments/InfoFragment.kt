package com.example.cleanapistruct.presentation.fragments

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cleanapistruct.MainViewModel
import com.example.cleanapistruct.common.extension.formatDt
import com.example.cleanapistruct.databinding.FragmentInfoBinding
import com.example.cleanapistruct.domain.model.Color
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class InfoFragment : BaseFragment<FragmentInfoBinding>(
    FragmentInfoBinding::inflate

) {
    private val args: InfoFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var myColor: Color
    override fun started() {
        val itemId = args.colorId
        getColorById(itemId)

        setContents()





    }

    private fun getColorById(id: Long) {
        mainViewModel.filterColorById(id)
        mainViewModel.myColor?.let {
            myColor = it
        }
    }

    private fun setContents() {
        val inputDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        val outputDate = SimpleDateFormat("hh:mm-dd/MM", Locale.getDefault())

        val date2 = myColor.dateCreated?.let { inputDate.parse(it) }
        val formattedDate = date2?.let { outputDate.format(it).formatDt() }

        binding.date.text = formattedDate
        binding.username.text = myColor.userName
        Glide.with(this).load(myColor.imageUrl).into(binding.imageView)
        myColor.rgb?.let {
            binding.colorbutton.setBackgroundColor(
                android.graphics
                    .Color
                    .rgb(
                        it.red!!,
                        it.green!!,
                        it.blue!!
                    )

            )
        }

    }




}