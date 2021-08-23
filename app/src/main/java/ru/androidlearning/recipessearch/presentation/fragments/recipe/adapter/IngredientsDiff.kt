package ru.androidlearning.recipessearch.presentation.fragments.recipe.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.recipessearch.presentation.ExtendedIngredientsPresentationData

object IngredientsDiff : DiffUtil.ItemCallback<ExtendedIngredientsPresentationData>() {
    override fun areItemsTheSame(oldItem: ExtendedIngredientsPresentationData, newItem: ExtendedIngredientsPresentationData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ExtendedIngredientsPresentationData, newItem: ExtendedIngredientsPresentationData): Boolean =
        oldItem == newItem
}
