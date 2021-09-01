package ru.androidlearning.recipessearch.data.repository.datasources.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.SearchResultsDTO
import ru.androidlearning.recipessearch.data.repository.datasources.cache.storage.RecipesStorage
import java.lang.RuntimeException
import javax.inject.Inject

class RecipesDataSourceCacheImpl @Inject constructor(
    private val recipesStorage: RecipesStorage
) : RecipesDataSourceCache {

    override fun retain(recipes: RecipesDTO): Completable =
        recipesStorage
            .recipesDao()
            .retain(recipes.recipes)

    override fun retain(recipe: RecipeDTO): Completable =
        recipesStorage
            .recipesDao()
            .retain(recipe)

    override fun getRandomRecipes(): Single<RecipesDTO> =
        recipesStorage
            .recipesDao()
            .getLastRandomRecipes()
            .map(::RecipesDTO)

    override fun getRecipeById(recipeId: Long): Maybe<RecipeDTO> =
        recipesStorage
            .recipesDao()
            .getRecipeById(recipeId)

    override fun searchRecipesByName(name: String): Single<SearchResultsDTO> =
        recipesStorage
            .recipesDao()
            .getRecipesByName(name)
            .map(SearchResultsDTO.Mapper::map)
}
