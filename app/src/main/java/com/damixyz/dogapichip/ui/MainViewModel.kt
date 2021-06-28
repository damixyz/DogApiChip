package com.damixyz.dogapichip.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.damixyz.domain.DogBreedImages
import com.damixyz.domain.DogBreedList
import com.damixyz.usecases.GetBreedImagesUseCase
import com.damixyz.usecases.GetBreedListUseCase
import com.damixyz.usecases.data.ImageScreenState
import com.damixyz.usecases.data.ListScreenState
import com.damixyz.usecases.data.ScreenState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(
    private val getBreedListUseCase: GetBreedListUseCase,
    private val getBreedImagesUseCase: GetBreedImagesUseCase
) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _dogBreedList: MutableLiveData<DogBreedList> = MutableLiveData()
    val dogBreedList: LiveData<DogBreedList>
        get() = _dogBreedList

    private val _dogImages: MutableLiveData<DogBreedImages> = MutableLiveData()
    val dogImages: LiveData<DogBreedImages>
        get() = _dogImages

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _loadingObservable: MutableLiveData<Boolean> = MutableLiveData()
    val loadingObservable: LiveData<Boolean>
        get() = _loadingObservable

    private val _activeError: MutableLiveData<Boolean> = MutableLiveData()
    val activeError: LiveData<Boolean>
        get() = _activeError

    fun getBreedList() {
        val disposable = getBreedListUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                processScreenState(it)
            }
        compositeDisposable.add(disposable)
    }

    fun getBreedImage(breedName: String) {
        val disposable = getBreedImagesUseCase.execute(breedName = breedName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                processScreenState(it)
            }
        compositeDisposable.add(disposable)
    }

    private fun processScreenState(screenState: ScreenState) {
        when (screenState) {
            is ListScreenState.Content -> {
                _dogBreedList.value = screenState.payload
                hideLoading()
            }
            is ImageScreenState.Content -> {
                _dogImages.value = screenState.payload
                hideLoading()
            }
            is ScreenState.Error -> {
                setActiveError()
                _errorMessage.value = screenState.errorMessages
                hideLoading()
            }
            is ScreenState.Loading -> {
                if (screenState.isLoading) {
                    unSetActiveError()
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        }
    }

    private fun hideLoading() {
        _loadingObservable.value = false
    }

    private fun showLoading() {
        _loadingObservable.value = true
    }

    private fun unSetActiveError() {
        _activeError.value = false
    }

    private fun setActiveError() {
        _activeError.value = true
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}