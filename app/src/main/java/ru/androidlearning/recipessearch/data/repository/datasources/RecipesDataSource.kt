package ru.androidlearning.recipessearch.data.repository.datasources

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.SearchResultsDTO

interface RecipesDataSource {
    fun getRandomRecipes(): Single<RecipesDTO>
    fun getRecipeById(recipeId: Long): Maybe<RecipeDTO>
    fun searchRecipesByName(name: String): Single<SearchResultsDTO>
}
