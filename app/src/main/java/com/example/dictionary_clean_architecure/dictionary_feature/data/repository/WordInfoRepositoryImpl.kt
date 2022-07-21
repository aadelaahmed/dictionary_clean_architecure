package com.example.dictionary_clean_architecure.dictionary_feature.data.repository


import com.example.dictionary_clean_architecure.core.util.Resource
import com.example.dictionary_clean_architecure.dictionary_feature.data.local.WordInfoDao
import com.example.dictionary_clean_architecure.dictionary_feature.data.remote.DictionaryApi
import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.WordInfo
import com.example.dictionary_clean_architecure.dictionary_feature.domain.use_case.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepositoryImpl(
    private val api : DictionaryApi,
    private val dao : WordInfoDao
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> =
        flow {
            emit(Resource.Loading())
            val localWordInfos = dao.getWordInfo(word).map { it.toWordInfo()}
            emit(Resource.Loading(data = localWordInfos))
            try {
                val remoteWordInfos = api.getWordInfo(word)
                dao.deleteWordInfos(remoteWordInfos.map{it.word})
                dao.insertNewWordInfo(remoteWordInfos.map{it.toWordInfoEntity()})
            }catch (httpException : HttpException){
                emit(
                    Resource.Error(
                        data = localWordInfos,
                        message = httpException.message()
                    )
                )
            }catch(ioException : IOException){
                emit(
                    Resource.Error(
                        data = localWordInfos,
                        message = ioException.message
                    )
                )
            }
            val newLocalWordInfos = dao.getWordInfo(word).map{it.toWordInfo()}
            emit(Resource.Success(data = newLocalWordInfos))
        }
}