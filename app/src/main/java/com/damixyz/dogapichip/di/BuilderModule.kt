package com.damixyz.dogapichip.di

import com.damixyz.data.repositories.DogBreedRepository
import com.damixyz.data.repositories.DogBreedRepositoryImp
import com.damixyz.data.source.DataSource
import com.damixyz.data.source.RemoteDataSource
import com.damixyz.usecases.GetBreedImagesUseCase
import com.damixyz.usecases.GetBreedImagesUseCaseImp
import com.damixyz.usecases.GetBreedListUseCase
import com.damixyz.usecases.GetBreedListUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ActivityComponent::class, ViewModelComponent::class)
@Module
abstract class BuilderModule {

    // Use cases
    @Binds
    abstract fun bindGetBreedImagesUseCase(getBreedImagesUseCaseImp: GetBreedImagesUseCaseImp):
            GetBreedImagesUseCase

    @Binds
    abstract fun bindGetBreedListUseCase(getBreedListUseCaseImp: GetBreedListUseCaseImp):
            GetBreedListUseCase

    // Repositories
    @Binds
    abstract fun bindDogBreedRepository(dogBreedRepositoryImp: DogBreedRepositoryImp):
            DogBreedRepository

    // Data Sources
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSource):
            DataSource
}