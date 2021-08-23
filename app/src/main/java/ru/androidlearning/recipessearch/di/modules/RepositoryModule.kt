package ru.androidlearning.recipessearch.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.androidlearning.recipessearch.data.repository.RecipesRepository
import ru.androidlearning.recipessearch.data.repository.RecipesRepositoryImpl
import ru.androidlearning.recipessearch.data.repository.datasources.RecipesDataSource
import ru.androidlearning.recipessearch.data.repository.datasources.RecipesDataSourceImpl
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRecipesRepository(recipesRepository: RecipesRepositoryImpl): RecipesRepository

    @Singleton
    @Binds
    fun bindRecipesDataSource(recipesDataSource: RecipesDataSourceImpl): RecipesDataSource
}
