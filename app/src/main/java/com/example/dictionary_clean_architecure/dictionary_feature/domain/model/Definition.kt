package com.example.dictionary_clean_architecure.dictionary_feature.domain.model
data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)