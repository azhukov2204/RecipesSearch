package ru.androidlearning.recipessearch.data

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("weightWatcherSmartPoints") val weightWatcherSmartPoints: Int,
    @SerializedName("extendedIngredients") val extendedIngredients: List<ExtendedIngredientsDTO>,
    @SerializedName("readyInMinutes") val readyInMinutes: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("image") val image: String,
    @SerializedName("imageType") val imageType: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("instructions") val instructions: String,
)

data class ExtendedIngredientsDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("aisle") val aisle: String,
    @SerializedName("image") val image: String,
    @SerializedName("consistency") val consistency: String,
    @SerializedName("name") val name: String,
    @SerializedName("nameClean") val nameClean: String,
    @SerializedName("original") val original: String,
    @SerializedName("originalString") val originalString: String,
    @SerializedName("originalName") val originalName: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("unit") val unit: String,
    @SerializedName("meta") val meta: List<String>,
    @SerializedName("metaInformation") val metaInformation: List<String>,
    @SerializedName("measures") val measures: MeasuresDTO
)

data class MeasuresDTO(
    @SerializedName("us") val us: UsDTO,
    @SerializedName("metric") val metric: MetricDTO
)

data class UsDTO(
    @SerializedName("amount") val amount: Double,
    @SerializedName("unitShort") val unitShort: String,
    @SerializedName("unitLong") val unitLong: String
)

data class MetricDTO(
    @SerializedName("amount") val amount: Double,
    @SerializedName("unitShort") val unitShort: String,
    @SerializedName("unitLong") val unitLong: String
)
