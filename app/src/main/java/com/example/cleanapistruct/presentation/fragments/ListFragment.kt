package com.example.cleanapistruct.presentation.fragments


import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanapistruct.MainViewModel
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(
    FragmentListBinding::inflate
) {


    private lateinit var adapter: ColorAdapter
    private lateinit var recyclerView: RecyclerView
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var connectivityObserver: ConnectivityObserver


    

    override fun onPause() {
        super.onPause()
        mainViewModel.setStateText(binding.searchBar.query.toString())

    }


    override fun started() {
        initAdapt()
        search()
        lifecycleScope.launch {
            mainViewModel.loading.collect {
                if (it){
                    binding.progressBar2.visibility = View.VISIBLE
                }else{
                    binding.progressBar2.visibility = View.INVISIBLE
                    if (mainViewModel.error.value !=""){
                        Toast.makeText(requireContext(), mainViewModel.error.value, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        binding.searchBar.setQuery(mainViewModel.getStateText(),true)
        connectivityObserver = NetworkConObserver(requireContext())
        connectivityObserver.observe()
            .onEach {
//                val curStatus = mainViewModel.getCurStatus() //es ar damchirda ....
                mainViewModel.setCurStatus(it)
            if (it == ConnectivityObserver.Status.LOST) {
                mainViewModel.setPrevStatus(ConnectivityObserver.Status.LOST)

            }
            else if(mainViewModel.getPrevStatus()==ConnectivityObserver.Status.LOST){
                mainViewModel.getAllColor(binding.searchBar.query.toString())
                mainViewModel.setPrevStatus(it)
            }
                println(it)



        }.launchIn(lifecycleScope)



    }

    private fun initAdapt() {
        adapter = ColorAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.onItemClick = {
            click(it)
        }
    }


    private fun click(color: Color) {
        color.id?.let {
            val action = ListFragmentDirections.actionListFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }

    }

    private fun update(data: List<Color> = emptyList()) {
        adapter.setList(data)

        Log.d("dataupdate", "update: $data")


    }

    override fun observers() {

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.success.collect {

                update(it)


            }

        }

    }
    private fun search(){
        val searchView = binding.searchBar
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.length > 2 || it.isEmpty())
                        mainViewModel.getAllColor(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val txt = newText ?: ""
                if (txt.length!=1 && txt.length!=2) {
                    mainViewModel.cancelDebounceJob()
                    mainViewModel.startDebounceJob()
                    mainViewModel.setTextFlow(txt)
                    //აქ ზუსტად არ ვიცი შეიძლება ცოტა ზედმეტიც მიწერია.
                    //მარა მუშაა რა

                }


                return true
            }

        })
    }
}