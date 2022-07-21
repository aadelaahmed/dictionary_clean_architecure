package com.example.dictionary_clean_architecure.dictionary_feature.domain.use_case

import com.example.dictionary_clean_architecure.core.util.Resource
import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word : String) : Flow<Resource<List<WordInfo>>>
}