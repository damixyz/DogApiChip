package com.damixyz.usecases.data

import com.damixyz.domain.DogBreedImages
import com.damixyz.domain.DogBreedList

sealed class ScreenState {
    data class Loading(val isLoading: Boolean) : ScreenState()
    data class Error(val errorMessages: String) : ScreenState()
    object Empty : ScreenState()
}

sealed class ListScreenState : ScreenState() {
    data class Content(val payload: DogBreedList) : ListScreenState()
}

sealed class ImageScreenState : ScreenState() {
    data class Content(val payload: DogBreedImages) : ImageScreenState()
}