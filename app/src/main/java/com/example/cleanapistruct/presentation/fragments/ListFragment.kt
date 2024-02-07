package com.example.cleanapistruct.presentation.fragments

import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.SearchView
import androidx.annotation.LongDef
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanapistruct.MainViewModel
import com.example.cleanapistruct.R
import com.example.cleanapistruct.databinding.FragmentListBinding
import com.example.cleanapistruct.domain.model.Color
import com.example.cleanapistruct.presentation.ConnectivityObserver
import com.example.cleanapistruct.presentation.NetworkConObserver
import com.example.cleanapistruct.presentation.adapter.ColorAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(
    FragmentListBinding::inflate
) {


    private lateinit var adapter: ColorAdapter
    private lateinit var recyclerView: RecyclerView
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var connectivityObserver: ConnectivityObserver


    private var data: List<Color>? = null
    private var searchJob: Job? = null
    private val delay: Long = 400




    override fun started() {
        listenToSearchView()
        connectivityObserver = NetworkConObserver(requireContext())
        connectivityObserver.observe().onEach {
            if (it == ConnectivityObserver.Status.AVAILABLE ||it ==ConnectivityObserver.Status.ONLYDATA) {

                mainViewModel.getAllColorName(binding.searchBar.query.toString())
            }



        }.launchIn(lifecycleScope)



    }

    private fun listenToSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                searchJob?.cancel()


                searchJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(delay)

                    newText?.let {
                        try {
                            mainViewModel.getAllColorName(it)
                        }catch (
                            e:Exception
                        ){
                            Log.e("error", "onQueryTextChange: error", )
                        }

                    }
                }

                return true
            }


        })
    }


    private fun click(color: Color) {
        color.id?.let {
            val action = ListFragmentDirections.actionListFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }

    }

    private fun update(data: List<Color> = emptyList()) {
        adapter = ColorAdapter(data)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.onItemClick = {
            click(it)
        }

    }

    override fun observers() {

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.success.collect {

                update(it)


            }

        }

    }
}