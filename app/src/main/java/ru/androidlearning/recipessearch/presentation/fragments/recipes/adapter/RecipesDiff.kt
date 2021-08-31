package ru.androidlearning.recipessearch.presentation.fragments.recipes.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.recipessearch.presentation.RecipePresentationData

object RecipesDiff : DiffUtil.ItemCallback<RecipePresentationData>() {
    override fun areItemsTheSame(oldItem: RecipePresentationData, newItem: RecipePresentationData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RecipePresentationData, newItem: RecipePresentationData): Boolean =
        oldItem == newItem
}
