package com.damixyz.data.repositories

import com.damixyz.data.source.DataSource
import com.damixyz.domain.DogBreedImages
import com.damixyz.domain.DogBreedList
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DogBreedRepositoryImp @Inject constructor(private val dataSource: DataSource) :
    DogBreedRepository {
    override fun getDogBreedList(): Observable<DogBreedList> {
        return dataSource.getDogBreedList().map { response ->
            DogBreedList(
                breedList = response.message::class.members.map {
                    it.name
                }
            )

        }.subscribeOn(Schedulers.io())
    }

    override fun getBreedImages(breedName: String): Observable<DogBreedImages> {
        return dataSource.getBreedImages(breedName = breedName).map { response ->
            DogBreedImages(
                breedImages = response.message
            )
        }.subscribeOn(Schedulers.io())
    }

}