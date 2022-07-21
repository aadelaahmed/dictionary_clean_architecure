package com.example.dictionary_clean_architecure.dictionary_feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary_clean_architecure.dictionary_feature.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWordInfo(newListInfos : List<WordInfoEntity>)

    @Query("DELETE FROM word_info WHERE word IN(:words)")
    suspend fun deleteWordInfos(words : List<String>)

    @Query("SELECT * FROM word_info where word LIKE '%' || :word || '%'")
    suspend fun getWordInfo(word : String) : List<WordInfoEntity>
}