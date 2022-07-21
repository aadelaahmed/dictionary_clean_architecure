package com.example.dictionary_clean_architecure.dictionary_feature.data.local.utl
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionary_clean_architecure.dictionary_feature.domain.model.Meaning
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningToJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>() {}.type
//            meanings::class.java
        ) ?: "[]"
    }

    @TypeConverter
    fun fromJsonToMeaning(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
//           json::class.java
        ) ?: emptyList()
    }
    /*fun fromJsonToMeanings(json : String) : Meaning{
        return Gson().fromJson(json,Meaning::class.java)
    }

    fun toJsonFromMeanings(meaning : Meaning) : String{
        return Gson().toJson(meaning)
    }*/

}