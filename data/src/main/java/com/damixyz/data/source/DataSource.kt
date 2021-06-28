package com.damixyz.data.source

import com.damixyz.data.data.BreedImageResponse
import com.damixyz.data.data.BreedListResponse
import io.reactivex.rxjava3.core.Observable

interface DataSource {
    fun getDogBreedList(): Observable<BreedListResponse>
    fun getBreedImages(breedName: String): Observable<BreedImageResponse>
}