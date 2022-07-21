package com.example.dictionary_clean_architecure.core.util

import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.WordInfo

data class WordInfoState(
    val wordInfos : List<WordInfo> = emptyList(),
    val isLoading : Boolean = false
)
