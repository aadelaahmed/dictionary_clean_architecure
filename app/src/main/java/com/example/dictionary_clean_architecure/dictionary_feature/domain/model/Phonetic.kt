package com.example.dictionary_clean_architecure.dictionary_feature.domain.model

data class Phonetic(
    val audio: String,
    val license: License,
    val sourceUrl: String,
    val text: String
)