package com.example.dictionary_clean_architecure.dictionary_feature.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String,
    val origin : String
)