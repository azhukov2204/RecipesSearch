package ru.androidlearning.recipessearch.presentation.fragments.recipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.databinding.RecipeListItemBinding
import ru.androidlearning.recipessearch.presentation.RecipePresentationData

class RecipesAdapter(private val onRecipeItemClickListener: OnRecipeItemClickListener) :
    ListAdapter<RecipePresentationData, RecipesAdapter.RecipeViewHolder>(RecipesDiff) {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: RecipeListItemBinding by viewBinding(RecipeListItemBinding::bind)
        fun bind(recipe: RecipePresentationData) {
            with(binding) {
                recipeTitle.text = recipe.title

                Glide.with(binding.recipePhoto.context)
                    .load(recipe.image)
                    .into(binding.recipePhoto)

                itemView.setOnClickListener {
                    onRecipeItemClickListener.onRecipeItemClick(recipe.id)
                }
            }
        }
    }

    interface OnRecipeItemClickListener {
        fun onRecipeItemClick(recipeId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recipe_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
