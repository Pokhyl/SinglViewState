package com.example.myproect.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproect.ListViewState
import com.example.myproect.Loading
import com.example.myproect.ProfileLoaded
import com.example.myproect.ProfileViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextLong

class MainViewModel : ViewModel() {
    val viewState = MutableLiveData<UploadViewState>()

    val viewState1 = MutableLiveData<ListViewState>()
    private var state: ListViewState
        get() {
            return viewState1.value!!
        }
        set(value) {
            viewState1.value = value
        }

    init {
        state = ListViewState(
            isLoading = true,
            items = emptyList()
        )
    }

    fun loadMore() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val newWords = getMoreWords()

            state = state.copy(
                isLoading = false,
                items = state.items + newWords
            )
        }
    }
    fun startUpload() {
        viewModelScope.launch {
            viewState.value = Initial
            viewState.value = UploadInProgress(10)
            delay1()
            viewState.value = UploadInProgress(30)
            delay1()
            viewState.value = UploadInProgress(42)
            delay1()
            viewState.value = UploadInProgress(50)
            delay1()

            if (Random.nextBoolean()) {
                viewState.value = UploadFailed
                return@launch
            }

            viewState.value = UploadInProgress(70)
            delay1()
            viewState.value = UploadInProgress(90)
            delay1()
            viewState.value = UploadInProgress(94)
            delay1()
            viewState.value = UploadInProgress(99)
            delay1()
            viewState.value = UploadInProgress(100)

            viewState.value = UploadSuccess
        }
    }

    private suspend fun delay1() {
        delay(Random.nextLong(500L..1200L))
    }

    private suspend fun getMoreWords(): List<String> {
        delay(1000L)
        return listOf("Nitwit", "Blubber", "Oddment", "Tweak")
    }

}
