package com.example.dictionary_clean_architecure.dictionary_feature.presentation
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary_clean_architecure.core.util.Resource
import com.example.dictionary_clean_architecure.core.util.WordInfoState
import com.example.dictionary_clean_architecure.dictionary_feature.domain.use_case.WordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    val wordInfoUseCase: WordInfoUseCase
) : ViewModel() {
    private val _searchState = mutableStateOf("")
    val searchState : State<String> = _searchState

    private val _state = mutableStateOf(WordInfoState())
    val state : State<WordInfoState> = _state

    private val _sharedEventFlow = MutableSharedFlow<EventWordInfo>()
    val sharedEventFlow = _sharedEventFlow.asSharedFlow()

    private var searchJob : Job? = null

    fun search(query : String){
        _searchState.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            //if within 500 milliseconds user writes new query , this search function will be invoked again
            //and cancel the last job and start new one
            wordInfoUseCase(query)
                .onEach {currentResource ->
                    //like observe on each emersion
                    when(currentResource){
                        is Resource.Loading ->
                        {
                            _state.value = state.value.copy(
                                wordInfos = currentResource.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success ->
                        {

                            _state.value = state.value.copy(
                                wordInfos = currentResource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error ->
                        {

                            _state.value = state.value.copy(
                                wordInfos = currentResource.data ?: emptyList(),
                                isLoading = false
                            )
                            _sharedEventFlow.emit(EventWordInfo.ShowSnackBar(
                                currentResource.message ?: "Unknown error"
                            )
                            )
                        }

                    }
                }.launchIn(this)
        }
    }

    sealed class EventWordInfo{
        data class ShowSnackBar(val message : String) : EventWordInfo()
    }

}