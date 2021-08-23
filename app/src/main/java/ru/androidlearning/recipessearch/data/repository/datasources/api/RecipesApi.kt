package ru.androidlearning.recipessearch.data.repository.datasources.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO

interface RecipesApi {
    @GET("random")
    fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") recipesNumber: Int
    ): Single<RecipesDTO>

    @GET("{recipeId}/information")
    fun getRecipeById(
        @Path("recipeId") recipeId: Long,
        @Query("apiKey") apiKey: String
    ): Single<RecipeDTO>
}
