package com.damixyz.usecases

import com.damixyz.usecases.data.ScreenState
import io.reactivex.rxjava3.core.Observable

interface GetBreedImagesUseCase {
    fun execute(breedName: String): Observable<ScreenState>
}