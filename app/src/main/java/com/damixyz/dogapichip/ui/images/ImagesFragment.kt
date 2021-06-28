package com.damixyz.dogapichip.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.damixyz.dogapichip.databinding.FragmentImagesBinding
import com.damixyz.dogapichip.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewAdapter: ImageAdapter = ImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImagesBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = activity
        binding.viewModel = mainViewModel

        binding.btnRetry.setOnClickListener {
            arguments?.getString("breedName")?.let { it1 -> mainViewModel.getBreedImage(it1) }
        }


        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        arguments?.getString("breedName")?.let { it1 -> mainViewModel.getBreedImage(it1) }

        // Success
        mainViewModel.dogImages.observe(requireActivity(), { item ->
            viewAdapter.setData(data = item.breedImages.shuffled().subList(0, 10))
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
}