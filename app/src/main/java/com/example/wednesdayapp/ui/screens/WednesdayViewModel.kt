package com.example.wednesdayapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.wednesdayapp.WednesdayApplication
import com.example.wednesdayapp.data.FrogsRepository
import com.example.wednesdayapp.model.FrogsData
import kotlinx.coroutines.launch
import okio.IOException

/**
 *  UI state for MainScreen
 */
sealed interface UiState{
    data class Success(val frogsData: List<FrogsData>) : UiState
    object Error : UiState
    object Loading : UiState
}

class WednesdayViewModel(private val frogsRepository: FrogsRepository) : ViewModel() {
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
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                UiState.Success(frogsRepository.getFrogs())
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }

    /**
     * Factory for [WednesdayViewModel] that takes [FrogsRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WednesdayApplication)
                val frogsRepository = application.container.frogsRepository
                WednesdayViewModel(frogsRepository = frogsRepository)
            }
        }
    }
}