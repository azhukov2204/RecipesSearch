package ru.androidlearning.recipessearch.presentation

import ru.androidlearning.recipessearch.data.SearchResultDTO

data class SearchResultPresentationData(
    val id: Long,
    val title: String?,
    val image: String?,
    val imageType: String?
) {
    object Mapper {
        fun map(searchResult: SearchResultDTO) = SearchResultPresentationData(
            searchResult.id,
            searchResult.title,
            searchResult.image,
            searchResult.imageType
        )
    }
}
