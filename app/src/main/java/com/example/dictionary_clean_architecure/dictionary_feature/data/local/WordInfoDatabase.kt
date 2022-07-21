package com.example.dictionary_clean_architecure.dictionary_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionary_clean_architecure.dictionary_feature.data.local.entity.WordInfoEntity
import com.example.dictionary_clean_architecure.dictionary_feature.data.local.utl.Converters

@Database(entities = [WordInfoEntity::class],version = 1)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {
    abstract val wordInfoDao : WordInfoDao
}