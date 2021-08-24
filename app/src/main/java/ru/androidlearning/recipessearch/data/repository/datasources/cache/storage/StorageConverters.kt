package ru.androidlearning.recipessearch.data.repository.datasources.cache.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.androidlearning.recipessearch.data.ExtendedIngredientsDTO

class StorageConverters {
    @TypeConverter
    fun fromExtendedIngredientsDTOtoJson(value: List<ExtendedIngredientsDTO>): String =
        Gson().toJson(value)

    @TypeConverter
    fun fromJsonToExtendedIngredientsDTO(value: String): List<ExtendedIngredientsDTO> {
        val listType = object : TypeToken<List<ExtendedIngredientsDTO>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
