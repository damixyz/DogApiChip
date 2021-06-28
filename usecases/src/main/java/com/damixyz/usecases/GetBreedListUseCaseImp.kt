package com.damixyz.usecases

import com.damixyz.data.repositories.DogBreedRepository
import com.damixyz.usecases.data.ListScreenState
import com.damixyz.usecases.data.ScreenState
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetBreedListUseCaseImp @Inject constructor(private val repository: DogBreedRepository) :
    GetBreedListUseCase {
    override fun execute(): Observable<ScreenState> {
        return repository.getDogBreedList().map { item ->
            ListScreenState.Content(payload = item) as ScreenState
        }.onErrorReturn { error ->
            ScreenState.Error(errorMessages = error.message ?: "Unknown Error")
        }.startWithItem(
            ScreenState.Loading(isLoading = true)
        )
    }
}