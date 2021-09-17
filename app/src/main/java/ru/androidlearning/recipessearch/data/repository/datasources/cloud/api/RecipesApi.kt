package ru.androidlearning.recipessearch.data.repository.datasources.cloud.api

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.SearchResultsDTO

interface RecipesApi {
    @GET("random")
    fun getRandomRecipes(
        @Query("number") recipesNumber: Int
    ): Single<RecipesDTO>

    @GET("{recipeId}/information")
    fun getRecipeById(
        @Path("recipeId") recipeId: Long
    ): Maybe<RecipeDTO>

    @GET("complexSearch")
    fun searchRecipesByName(
        @Query("query") query: String
    ): Single<SearchResultsDTO>
}
