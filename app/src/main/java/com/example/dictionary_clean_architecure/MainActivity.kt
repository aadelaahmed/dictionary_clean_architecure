package com.example.dictionary_clean_architecure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionary_clean_architecure.dictionary_feature.presentation.WordInfoItem
import com.example.dictionary_clean_architecure.dictionary_feature.presentation.WordInfoViewModel
import com.example.dictionary_clean_architecure.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryTheme {
                val viewModel : WordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()
                LaunchedEffect(key1 = true){
                    viewModel.sharedEventFlow.collectLatest { event ->
                        when(event){
                            is WordInfoViewModel.EventWordInfo.ShowSnackBar ->
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                        }
                    }
                }
                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(
                                value = viewModel.searchState.value,
                                onValueChange = viewModel::search,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Search ...")
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(){
                                items(state.wordInfos.size){ index ->
                                    val item = state.wordInfos[index]
                                    WordInfoItem(wordInfo = item)
                                    if(index > 0)
                                        Spacer(modifier = Modifier.height(8.dp))
                                    if(index < state.wordInfos.size - 1)
                                        Divider()
                                }
                            }
                        }
                    }
                    if(state.isLoading)
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                }
            }
        }
    }


}