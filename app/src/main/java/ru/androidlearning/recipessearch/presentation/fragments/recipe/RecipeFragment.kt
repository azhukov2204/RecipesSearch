package ru.androidlearning.recipessearch.presentation.fragments.recipe

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import moxy.ktx.moxyPresenter
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.databinding.FragmentRecipeBinding
import ru.androidlearning.recipessearch.di.assisted_factoryes.RecipePresenterFactory
import ru.androidlearning.recipessearch.presentation.RecipePresentationData
import ru.androidlearning.recipessearch.presentation.abstract_templates.DaggerMvpFragment
import ru.androidlearning.recipessearch.presentation.fragments.recipe.adapter.IngredientsAdapter
import javax.inject.Inject

private const val ARG_RECIPE_ID = "recipe_id"

class RecipeFragment : DaggerMvpFragment(R.layout.fragment_recipe), RecipeView {

    private val recipeId: Long? by lazy { arguments?.getLong(ARG_RECIPE_ID) }
    private val binding: FragmentRecipeBinding by viewBinding(FragmentRecipeBinding::bind)
    private val adapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    @Inject
    lateinit var recipePresenterFactory: RecipePresenterFactory
    val recipePresenter by moxyPresenter { recipePresenterFactory.create(recipeId) }

    companion object {
        fun newInstance(recipeId: Long) =
            RecipeFragment().apply {
                arguments = bundleOf(ARG_RECIPE_ID to recipeId)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ingredientsRecyclerView.adapter = adapter
        (context as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            recipePresenter.closeFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showRecipe(recipePresentationData: RecipePresentationData) {
        with(binding) {
            Glide.with(requireContext())
                .load(recipePresentationData.image)
                .into(recipePhoto)
            recipeTitle.text = recipePresentationData.title
            adapter.submitList(recipePresentationData.extendedIngredients)
            instruction.text = recipePresentationData.instructions?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
            toolbarLayout.title = recipePresentationData.title
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, getString(R.string.error_occurred_text) + message, Toast.LENGTH_SHORT).show()
    }
}
