package ru.androidlearning.recipessearch.data

import com.google.gson.annotations.SerializedName

data class RecipesDTO(
    @SerializedName("recipes") val recipes: List<RecipeDTO>
)
