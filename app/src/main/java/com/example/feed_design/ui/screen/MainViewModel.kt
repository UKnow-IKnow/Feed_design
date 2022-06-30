package com.example.feed_design.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feed_design.data.api.model.Image
import com.example.feed_design.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {

    private val _state = MutableStateFlow(emptyList<Image>())
    val state: StateFlow<List<Image>>
        get() = _state

    init {
        viewModelScope.launch {
            val images = imageRepository.getImages()
            _state.value = images
        }
    }
    
}