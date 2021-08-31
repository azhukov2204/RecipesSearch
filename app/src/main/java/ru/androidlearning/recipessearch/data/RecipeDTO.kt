package ru.androidlearning.recipessearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "recipes"
)
data class RecipeDTO(
    @PrimaryKey
    @ColumnInfo(name = "id") @SerializedName("id") val id: Long,
    @ColumnInfo(name = "title") @SerializedName("title") val title: String,
    @ColumnInfo(name = "weight_watcher_smart_points") @SerializedName("weightWatcherSmartPoints") val weightWatcherSmartPoints: Int?,
    @ColumnInfo(name = "extended_ingredients") @SerializedName("extendedIngredients") val extendedIngredients: List<ExtendedIngredientsDTO>?,
    @ColumnInfo(name = "ready_in_minutes") @SerializedName("readyInMinutes") val readyInMinutes: Int?,
    @ColumnInfo(name = "servings") @SerializedName("servings") val servings: Int?,
    @ColumnInfo(name = "image") @SerializedName("image") val image: String?,
    @ColumnInfo(name = "image_type") @SerializedName("imageType") val imageType: String?,
    @ColumnInfo(name = "summary") @SerializedName("summary") val summary: String?,
    @ColumnInfo(name = "instructions") @SerializedName("instructions") val instructions: String?,
    @ColumnInfo(name = "saved_time") var savedTime: Long?
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
