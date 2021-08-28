package ru.androidlearning.recipessearch.data

import com.google.gson.annotations.SerializedName

data class SearchResultsDTO(
    @SerializedName("results") val results: List<SearchResultDTO>,
    @SerializedName("offset") val offset: Int?,
    @SerializedName("number") val number: Int?,
    @SerializedName("totalResults") val totalResults: Int?
) {
    object Mapper {
        fun map(recipes: List<RecipeDTO>): SearchResultsDTO =
            SearchResultsDTO(
                recipes.map { recipeDTO ->
                    SearchResultDTO(
                        recipeDTO.id,
                        recipeDTO.title,
                        recipeDTO.image,
                        recipeDTO.imageType
                    )
                },
                null,
                null,
                null
            )
    }
}

data class SearchResultDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String?,
    @SerializedName("imageType") val imageType: String?
)
