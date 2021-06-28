package com.damixyz.data.repositories

import com.damixyz.domain.DogBreedImages
import com.damixyz.domain.DogBreedList
import io.reactivex.rxjava3.core.Observable

interface DogBreedRepository {
    fun getDogBreedList(): Observable<DogBreedList>
    fun getBreedImages(breedName: String): Observable<DogBreedImages>
}