package ru.androidlearning.recipessearch.di.modules

import dagger.Binds
import dagger.Module
import ru.androidlearning.recipessearch.data.repository.RecipesRepository
import ru.androidlearning.recipessearch.data.repository.RecipesRepositoryImpl
import ru.androidlearning.recipessearch.data.repository.datasources.cache.RecipesDataSourceCache
import ru.androidlearning.recipessearch.data.repository.datasources.cache.RecipesDataSourceCacheImpl
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.RecipesDataSourceCloud
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.RecipesDataSourceCloudImpl

@Module
interface RepositoryModule {

    @Binds
    fun bindRecipesRepository(recipesRepository: RecipesRepositoryImpl): RecipesRepository

    @Binds
    fun bindRecipesDataSourceCloud(recipesDataSourceCloud: RecipesDataSourceCloudImpl): RecipesDataSourceCloud

    @Binds
    fun bindRecipesDataSourceCache(recipesDataSourceCache: RecipesDataSourceCacheImpl): RecipesDataSourceCache
}
