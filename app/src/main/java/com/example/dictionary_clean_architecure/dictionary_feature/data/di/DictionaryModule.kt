package com.example.dictionary_clean_architecure.dictionary_feature.data.di
import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dictionary_clean_architecure.dictionary_feature.data.local.WordInfoDatabase
import com.example.dictionary_clean_architecure.dictionary_feature.data.local.utl.Converters
import com.example.dictionary_clean_architecure.dictionary_feature.data.local.utl.GsonParser
import com.example.dictionary_clean_architecure.dictionary_feature.data.remote.DictionaryApi
import com.example.dictionary_clean_architecure.dictionary_feature.data.remote.DictionaryApi.Companion.BASE_URL
import com.example.dictionary_clean_architecure.dictionary_feature.data.repository.WordInfoRepositoryImpl
import com.example.dictionary_clean_architecure.dictionary_feature.domain.use_case.WordInfoRepository
import com.example.dictionary_clean_architecure.dictionary_feature.domain.use_case.WordInfoUseCase
import com.google.gson.Gson

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DictionaryModule {

    @Singleton
    @Provides
    fun provideDictionaryApi(): DictionaryApi =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)

    @Singleton
    @Provides
    fun provideWordInfoUseCase(repository: WordInfoRepository): WordInfoUseCase =
        WordInfoUseCase(repository)

    @Singleton
    @Provides
    fun provideWordInfoRepository(
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepository =
        WordInfoRepositoryImpl(api, db.wordInfoDao)

    @Singleton
    @Provides
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase =
        Room
            .databaseBuilder(
                app,
                WordInfoDatabase::class.java,
                "Word_INFO_Database"
            ).fallbackToDestructiveMigration()
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
}