package ru.androidlearning.recipessearch.data.repository.datasources

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.repository.datasources.api.RecipesApi
import javax.inject.Inject
import javax.inject.Named

class RecipesDataSourceImpl @Inject constructor(
    private val recipesApi: RecipesApi,
    @Named("spoonacular_api_key") private val apiKey: String,
    @Named("random_recipes_number") private val randomRecipesNumber: Int
) : RecipesDataSource {

    override fun getRandomRecipes(): Single<RecipesDTO> =
        recipesApi.getRandomRecipes(apiKey, randomRecipesNumber)

    override fun getRecipeById(recipeId: Long): Single<RecipeDTO> =
        recipesApi.getRecipeById(recipeId, apiKey)
}
