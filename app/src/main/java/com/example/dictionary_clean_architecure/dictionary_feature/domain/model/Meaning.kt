package com.example.dictionary_clean_architecure.dictionary_feature.domain.model

data class Meaning(
    val definition: List<Definition>,
    val partOfSpeech: String,
)