package com.example.cleanapistruct.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cleanapistruct.databinding.FragmentButtonBinding
import com.example.cleanapistruct.presentation.ConnectivityObserver
import com.example.cleanapistruct.presentation.NetworkConObserver
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ButtonFragment : Fragment() {
    private var _binding: FragmentButtonBinding? = null
    private val binding get() = _binding!!
    private lateinit var connectivityObserver: ConnectivityObserver




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE

        binding.button.setOnClickListener {

            val nav = findNavController()
            val action = ButtonFragmentDirections.actionButtonFragmentToListFragment()

            nav.navigate(action)



        }
        connectivityObserver = NetworkConObserver(requireContext())
        connectivityObserver.observe().onEach {

            if (it != ConnectivityObserver.Status.AVAILABLE ) {
                binding.button.visibility = View.VISIBLE

            }

        }.launchIn(lifecycleScope)

    }


}