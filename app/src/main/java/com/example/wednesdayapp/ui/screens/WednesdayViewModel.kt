package com.example.wednesdayapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wednesdayapp.model.FrogsData

/**
 *  UI state for MainScreen
 */
sealed interface UiState{
    data class Success(val frogsData: List<FrogsData>) : UiState
    object Error : UiState
    object Loading : UiState
}

class WednesdayViewModel : ViewModel() {
    /**The mutable State that stores the status of the most recent request**/
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    /**
     * Call getFrogsData() on init so we can display status immediately
     */
    init {
        getFrogsData()
    }

    fun getFrogsData() {
        // TODO: Create data logic and then write logic for getFrogsData
    }
}