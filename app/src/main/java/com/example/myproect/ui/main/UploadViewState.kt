package com.example.myproect.ui.main

sealed class UploadViewState

object Initial : UploadViewState()

data class UploadInProgress(val percentage: Int) : UploadViewState()

object UploadFailed : UploadViewState()

object UploadSuccess : UploadViewState()