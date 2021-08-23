package ru.androidlearning.recipessearch.presentation

import ru.androidlearning.recipessearch.data.*

data class RecipePresentationData(
    val id: Long,
    val title: String?,
    val image: String?,
    val imageType: String?,
    val weightWatcherSmartPoints: Int?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    val instructions: String?,
    val extendedIngredients: List<ExtendedIngredientsPresentationData>
) {
    object Mapper {
        fun map(recipeDTO: RecipeDTO): RecipePresentationData =
            RecipePresentationData(
                recipeDTO.id,
                recipeDTO.title,
                recipeDTO.image,
                recipeDTO.imageType,
                recipeDTO.weightWatcherSmartPoints,
                recipeDTO.readyInMinutes,
                recipeDTO.servings,
                recipeDTO.summary,
                recipeDTO.instructions,
                recipeDTO.extendedIngredients.map(ExtendedIngredientsPresentationData.Mapper::map)
            )
    }
}

data class ExtendedIngredientsPresentationData(
    val id: Long,
    val aisle: String?,
    val image: String?,
    val consistency: String?,
    val name: String?,
    val nameClean: String?,
    val original: String?,
    val originalString: String?,
    val originalName: String?,
    val amount: Double?,
    val unit: String?,
    val meta: List<String>?,
    val metaInformation: List<String>?,
    val measures: MeasuresPresentationData?
) {
    object Mapper {
        fun map(extendedIngredientsDTO: ExtendedIngredientsDTO): ExtendedIngredientsPresentationData =
            ExtendedIngredientsPresentationData(
                extendedIngredientsDTO.id,
                extendedIngredientsDTO.aisle,
                extendedIngredientsDTO.image,
                extendedIngredientsDTO.consistency,
                extendedIngredientsDTO.name,
                extendedIngredientsDTO.nameClean,
                extendedIngredientsDTO.original,
                extendedIngredientsDTO.originalString,
                extendedIngredientsDTO.originalName,
                extendedIngredientsDTO.amount,
                extendedIngredientsDTO.unit,
                extendedIngredientsDTO.meta,
                extendedIngredientsDTO.metaInformation,
                MeasuresPresentationData.Mapper.map(extendedIngredientsDTO.measures)
            )
    }
}

data class MeasuresPresentationData(
    val us: UsPresentationData?,
    val metric: MetricPresentationData?
) {
    object Mapper {
        fun map(measuresDTO: MeasuresDTO): MeasuresPresentationData =
            MeasuresPresentationData(
                UsPresentationData.Mapper.map(measuresDTO.us),
                MetricPresentationData.Mapper.map(measuresDTO.metric)
            )
    }
}

data class UsPresentationData(
    val amount: Double?,
    val unitShort: String?,
    val unitLong: String?
) {
    object Mapper {
        fun map(usDTO: UsDTO): UsPresentationData =
            UsPresentationData(
                usDTO.amount,
                usDTO.unitShort,
                usDTO.unitLong
            )
    }
}

data class MetricPresentationData(
    val amount: Double?,
    val unitShort: String?,
    val unitLong: String?
) {
    object Mapper {
        fun map(metricDTO: MetricDTO): MetricPresentationData =
            MetricPresentationData(
                metricDTO.amount,
                metricDTO.unitShort,
                metricDTO.unitLong
            )
    }
}
