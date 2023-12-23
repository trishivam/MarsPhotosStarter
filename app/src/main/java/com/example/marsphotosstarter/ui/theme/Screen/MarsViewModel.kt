package com.example.marsphotosstarter.ui.theme.Screen

import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotosstarter.MarsPhotoApplication
import com.example.marsphotosstarter.data.MarsPhotosRepository
import com.example.marsphotosstarter.data.NetworkMarsPhotosRepository
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository): ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }
//    val marsPhotosRepository = NetworkMarsPhotosRepository()


    private fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading

            try{
                val listResult = marsPhotosRepository.getMarsPhotos()
                marsUiState = MarsUiState.Success("Success: ${listResult.size} Mars photos retrieved")
            }
            catch (e: IOException){
                marsUiState = MarsUiState.Error
            }
//                catch (e: HttpException) {
//                    MarsUiState.Error
//                }
        }
    }
    companion object MarsViewModel {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotoApplication)
                val marsPhotosRepository = application.container.marsPhotoRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}





