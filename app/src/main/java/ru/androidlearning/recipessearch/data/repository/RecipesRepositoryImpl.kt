package ru.androidlearning.recipessearch.data.repository

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.recipessearch.data.RecipeDTO
import ru.androidlearning.recipessearch.data.RecipesDTO
import ru.androidlearning.recipessearch.data.repository.datasources.RecipesDataSource
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesDataSource: RecipesDataSource
) : RecipesRepository {

    override fun getRandomRecipes(): Single<RecipesDTO> =
        recipesDataSource.getRandomRecipes()

    override fun getRecipeById(recipeId: Long): Single<RecipeDTO> =
        recipesDataSource.getRecipeById(recipeId)
}
