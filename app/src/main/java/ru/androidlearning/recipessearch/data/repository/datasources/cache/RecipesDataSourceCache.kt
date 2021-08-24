package ru.androidlearning.recipessearch.data.repository.datasources.cache

import io.reactivex.rxjava3.core.Completable
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.repository.datasources.RecipesDataSource

interface RecipesDataSourceCache : RecipesDataSource {
    fun retain(recipes: RecipesDTO): Completable
}
