package com.example.dictionary_clean_architecure.dictionary_feature.domain.use_case
import com.example.dictionary_clean_architecure.core.util.Resource
import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WordInfoUseCase (
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank())
            return flow { }
        return repository.getWordInfo(word)
    }

}