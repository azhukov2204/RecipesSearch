package ru.androidlearning.recipessearch.presentation.fragments.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.databinding.SearchResultsListItemBinding
import ru.androidlearning.recipessearch.presentation.SearchResultPresentationData

class SearchResultsAdapter(
    private val onSearchResultClickListener: OnSearchResultClickListener
) : ListAdapter<SearchResultPresentationData, SearchResultsAdapter.SearchResultsViewHolder>(SearchResultsDiff) {

    inner class SearchResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding by viewBinding(SearchResultsListItemBinding::bind)

        fun bind(searchResultPresentationData: SearchResultPresentationData) {
            binding.recipeTitle.text = searchResultPresentationData.title
            itemView.setOnClickListener { onSearchResultClickListener.onSearchResultItemClick(searchResultPresentationData.id) }
            Glide.with(binding.recipeImage.context)
                .load(searchResultPresentationData.image)
                .into(binding.recipeImage)
        }
    }

    interface OnSearchResultClickListener {
        fun onSearchResultItemClick(recipeId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder =
        SearchResultsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_results_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
