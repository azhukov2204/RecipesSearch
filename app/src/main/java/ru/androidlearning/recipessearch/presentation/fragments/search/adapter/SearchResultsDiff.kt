package ru.androidlearning.recipessearch.presentation.fragments.search.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.recipessearch.presentation.SearchResultPresentationData

object SearchResultsDiff : DiffUtil.ItemCallback<SearchResultPresentationData>() {
    override fun areItemsTheSame(oldItem: SearchResultPresentationData, newItem: SearchResultPresentationData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchResultPresentationData, newItem: SearchResultPresentationData): Boolean =
        oldItem == newItem
}
