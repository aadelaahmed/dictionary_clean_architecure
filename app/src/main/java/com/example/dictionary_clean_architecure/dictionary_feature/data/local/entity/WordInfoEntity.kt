package com.example.dictionary_clean_architecure.dictionary_feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.Meaning
import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.WordInfo

@Entity(tableName = "word_info")
data class WordInfoEntity(
    val word: String,
    val origin: String,
    val phonetic: String,
    val meanings: List<Meaning>,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo = WordInfo(
        word = word,
        origin = origin,
        phonetic = phonetic,
        meanings = meanings
    )
}
