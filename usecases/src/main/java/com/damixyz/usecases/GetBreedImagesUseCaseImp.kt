package com.damixyz.usecases

import com.damixyz.data.repositories.DogBreedRepository
import com.damixyz.usecases.data.ImageScreenState
import com.damixyz.usecases.data.ScreenState
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetBreedImagesUseCaseImp @Inject constructor(private val repository: DogBreedRepository) :
    GetBreedImagesUseCase {
    override fun execute(breedName: String): Observable<ScreenState> {
        return repository.getBreedImages(breedName = breedName).map { item ->
            ImageScreenState.Content(payload = item) as ScreenState
        }.onErrorReturn { error ->
            ScreenState.Error(errorMessages = error.message ?: "Unknown Error")
        }.startWithItem(
            ScreenState.Loading(isLoading = true)
        )
    }
}