package ru.androidlearning.recipessearch.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.androidlearning.recipessearch.data.repository.datasources.cache.storage.RecipesStorage
import javax.inject.Singleton

@Module
class StorageModule {
    @Singleton
    @Provides
    fun provideGitHubStorage(context: Context): RecipesStorage = Room.databaseBuilder(context, RecipesStorage::class.java, "recipes.db")
        .build()
}
