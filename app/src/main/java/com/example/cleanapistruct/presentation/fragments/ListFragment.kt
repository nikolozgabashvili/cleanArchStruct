package com.example.cleanapistruct.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cleanapistruct.R
import com.example.cleanapistruct.databinding.FragmentListBinding
import com.example.cleanapistruct.presentation.MainViewModel
import com.example.cleanapistruct.presentation.adapter.ColorAdapter


class ListFragment : Fragment() {

    private var _Binding: FragmentListBinding? = null
    private val binding get() = _Binding!!

    private lateinit var adapter: ColorAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _Binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = defaultViewModelProviderFactory.create(MainViewModel::class.java)

        println(viewModel.getData())

        listeners()
    }

    private fun listeners() {

        adapter.onItemClickListener = { item ->

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _Binding = null
    }


}