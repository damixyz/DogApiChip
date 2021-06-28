package com.damixyz.data.api

import com.damixyz.data.data.BreedImageResponse
import com.damixyz.data.data.BreedListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("api/breeds/list/all/")
    fun getDogBreed(): Single<BreedListResponse>

    @GET("api/breed/{breedName}/images")
    fun getBreedImage(
        @Path("breedName") breedName: String
    ): Single<BreedImageResponse>
}