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

class MainViewModel : ViewModel() {

    val viewState = MutableLiveData<ListViewState>()
    private var state: ListViewState
        get() {
            return viewState.value!!
        }
        set(value) {
            viewState.value = value
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

    private suspend fun getMoreWords(): List<String> {
        delay(1000L)
        return listOf("Nitwit", "Blubber", "Oddment", "Tweak")
    }

}
