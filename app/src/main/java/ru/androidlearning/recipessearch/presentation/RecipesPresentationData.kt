package ru.androidlearning.recipessearch.presentation

import ru.androidlearning.recipessearch.data.RecipesDTO

data class RecipesPresentationData(
    val recipes: List<RecipePresentationData>
) {
    object Mapper {
        fun map(recipesDTO: RecipesDTO): RecipesPresentationData =
            RecipesPresentationData(recipesDTO.recipes.map(RecipePresentationData.Mapper::map))
    }
}
