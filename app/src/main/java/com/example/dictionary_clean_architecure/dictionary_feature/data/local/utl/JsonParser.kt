package com.example.dictionary_clean_architecure.dictionary_feature.data.local.utl

import java.lang.reflect.Type

interface JsonParser {
    fun<T> fromJson(json : String, type : Type) : T?
    fun<T> toJson(obj : T,type : Type) : String?
}