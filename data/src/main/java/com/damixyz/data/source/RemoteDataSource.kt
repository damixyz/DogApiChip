package com.damixyz.data.source

import com.damixyz.data.api.DogApi
import com.damixyz.data.data.BreedImageResponse
import com.damixyz.data.data.BreedListResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: DogApi) : DataSource {
    override fun getDogBreedList(): Observable<BreedListResponse> {
        return service.getDogBreed().toObservable()
    }

    override fun getBreedImages(breedName: String): Observable<BreedImageResponse> {
        return service.getBreedImage(breedName = breedName).toObservable()
    }
}