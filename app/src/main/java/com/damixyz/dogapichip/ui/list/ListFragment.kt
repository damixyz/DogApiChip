package com.damixyz.dogapichip.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.damixyz.dogapichip.databinding.FragmentListBinding
import com.damixyz.dogapichip.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListFragment : Fragment(), OnClickListener {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewAdapter: ListAdapter = ListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = activity
        binding.viewModel = mainViewModel

        binding.btnRetry.setOnClickListener {
            mainViewModel.getBreedList()
        }

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        mainViewModel.getBreedList()

        // Success
        mainViewModel.dogBreedList.observe(requireActivity(), { item ->
            viewAdapter.setData(data = item.breedList)
        })

        // Loading
        mainViewModel.loadingObservable.observe(requireActivity(), { loading ->
            when (loading) {
                true -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                false -> {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        })


        // Error
        mainViewModel.activeError.observe(requireActivity(), { error ->
            when (error) {
                true -> {
                    binding.llErrorContainer.visibility = View.VISIBLE
                }
                false -> {
                    binding.llErrorContainer.visibility = View.GONE
                }
            }
        })

        return binding.root
    }

    override fun onItemClick(breedName: String) {
        Timber.d("✅ $breedName")
        val action =
            ListFragmentDirections.actionListFragmentToImagesFragment(breedName = breedName)
        findNavController().navigate(action)
    }
}