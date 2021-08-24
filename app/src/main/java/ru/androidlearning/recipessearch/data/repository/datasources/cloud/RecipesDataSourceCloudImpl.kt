package ru.androidlearning.recipessearch.data.repository.datasources.cloud

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.repository.datasources.cloud.api.RecipesApi
import javax.inject.Inject
import javax.inject.Named

class RecipesDataSourceCloudImpl @Inject constructor(
    private val recipesApi: RecipesApi,
    @Named("spoonacular_api_key") private val apiKey: String,
    @Named("random_recipes_number") private val randomRecipesNumber: Int
) : RecipesDataSourceCloud {

    override fun getRandomRecipes(): Single<RecipesDTO> =
        recipesApi.getRandomRecipes(apiKey, randomRecipesNumber)

    override fun getRecipeById(recipeId: Long): Maybe<RecipeDTO> =
        recipesApi.getRecipeById(recipeId, apiKey)
}
