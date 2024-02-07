package com.example.cleanapistruct.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.cleanapistruct.MainViewModel

typealias inflater<T> = (layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflater: inflater<VB>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun started()
    protected open fun observers() {}

    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = this.inflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        started()
        observers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}