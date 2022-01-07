package com.example.myproect

sealed class ProfileViewState

object Loading : ProfileViewState()

object Error : ProfileViewState()

data class ProfileLoaded(
    val name: String,
    val email: String
) : ProfileViewState()