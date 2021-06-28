package com.damixyz.dogapichip.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.damixyz.usecases.GetBreedImagesUseCase
import com.damixyz.usecases.GetBreedListUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getBreedListUseCase: GetBreedListUseCase,
    private val getBreedImagesUseCase: GetBreedImagesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                getBreedImagesUseCase = getBreedImagesUseCase,
                getBreedListUseCase = getBreedListUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}