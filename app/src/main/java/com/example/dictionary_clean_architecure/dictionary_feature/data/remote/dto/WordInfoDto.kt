package com.example.dictionary_clean_architecure.dictionary_feature.data.remote.dto

import com.example.dictionary_clean_architecure.dictionary_feature.data.local.entity.WordInfoEntity


data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
) {

    fun toWordInfoEntity() : WordInfoEntity =
        WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word,
            origin = origin,
        )
}