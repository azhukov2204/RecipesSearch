package ru.androidlearning.recipessearch.data.repository.datasources.cache.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.repository.datasources.cache.storage.dao.RecipesDao

@Database(exportSchema = false, entities = [RecipeDTO::class], version = 1)
@TypeConverters(StorageConverters::class)
abstract class RecipesStorage : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}
