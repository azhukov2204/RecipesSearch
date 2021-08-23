package ru.androidlearning.recipessearch.data.repository.datasources

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO

interface RecipesDataSource {
    fun getRandomRecipes(): Single<RecipesDTO>
    fun getRecipeById(recipeId: Long): Single<RecipeDTO>
}
