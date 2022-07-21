package com.example.dictionary_clean_architecure.dictionary_feature.data.remote.dto

import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.Phonetic

data class PhoneticDto(
    val audio: String,
    val licenseDto: LicenseDto,
    val sourceUrl: String,
    val text: String
){
    fun toPhonetic() : Phonetic
     = Phonetic(
        audio = audio,
        license = licenseDto.toLicense(),
        sourceUrl = sourceUrl,
        text = text
     )
}