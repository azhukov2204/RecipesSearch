package ru.androidlearning.recipessearch.presentation.fragments.recipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.databinding.IngredientListItemBinding
import ru.androidlearning.recipessearch.presentation.ExtendedIngredientsPresentationData
import java.util.*

class IngredientsAdapter : ListAdapter<ExtendedIngredientsPresentationData, IngredientsAdapter.IngredientsViewHolder>(IngredientsDiff) {
    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: IngredientListItemBinding by viewBinding(IngredientListItemBinding::bind)

        fun bind(ingredient: ExtendedIngredientsPresentationData) {
            with(binding) {
                ingredientName.text = ingredient.name
                val amount = ingredient.measures?.metric?.amount
                val unitShort = ingredient.measures?.metric?.unitShort
                ingredientsCount.text = String.format(Locale.getDefault(), "%.2f %s", amount, unitShort)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder =
        IngredientsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.ingredient_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
