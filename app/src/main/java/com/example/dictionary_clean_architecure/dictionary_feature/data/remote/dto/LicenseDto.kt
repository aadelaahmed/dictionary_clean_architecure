package com.example.dictionary_clean_architecure.dictionary_feature.data.remote.dto

import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.License


data class LicenseDto(
    val name: String,
    val url: String
){
    fun toLicense() : License = License(
        name= name,
        url = url
    )
}