package com.example.dictionary_clean_architecure.dictionary_feature.data.remote.dto

import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.Meaning

data class MeaningDto(
    val definitionDtos: List<DefinitionDto>,
    val partOfSpeech: String,
) {
    fun toMeaning(): Meaning = Meaning(
        definition = definitionDtos.map { it.toDefinition() },
        partOfSpeech = partOfSpeech,
    )
}