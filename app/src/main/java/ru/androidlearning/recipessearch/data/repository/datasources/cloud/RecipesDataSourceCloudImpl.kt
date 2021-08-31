package ru.androidlearning.recipessearch.data.repository.datasources.cloud

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.SearchResultsDTO
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.api.RecipesApi
import javax.inject.Inject

const val RANDOM_RECIPES_NUMBER = 10

class RecipesDataSourceCloudImpl @Inject constructor(
    private val recipesApi: RecipesApi
) : RecipesDataSourceCloud {

    override fun getRandomRecipes(): Single<RecipesDTO> =
        recipesApi.getRandomRecipes(RANDOM_RECIPES_NUMBER)

    override fun getRecipeById(recipeId: Long): Maybe<RecipeDTO> =
        recipesApi.getRecipeById(recipeId)

    override fun searchRecipesByName(name: String): Single<SearchResultsDTO> =
        recipesApi.searchRecipesByName(name)
}
