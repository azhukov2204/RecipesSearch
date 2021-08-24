package ru.androidlearning.recipessearch.presentation.fragments.recipes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.databinding.FragmentRecipesBinding
import ru.androidlearning.recipessearch.presentation.RecipesPresentationData
import ru.androidlearning.recipessearch.presentation.abstract_templates.DaggerMvpFragment
import ru.androidlearning.recipessearch.presentation.fragments.recipes.adapter.RecipesAdapter
import javax.inject.Inject

class RecipesFragment() : DaggerMvpFragment(R.layout.fragment_recipes), RecipesView, RecipesAdapter.OnRecipeItemClickListener {
    companion object {
        fun newInstance() = RecipesFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var recipesPresenter: RecipesPresenter

    @ProvidePresenter
    fun providePresenter(): RecipesPresenter = recipesPresenter

    private val binding: FragmentRecipesBinding by viewBinding(FragmentRecipesBinding::bind)
    private val recipesAdapter = RecipesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipeRecyclerView.adapter = recipesAdapter
        (context as AppCompatActivity).apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
        }
    }

    override fun showRecipes(recipesPresentationData: RecipesPresentationData) {
        recipesAdapter.submitList(recipesPresentationData.recipes)
    }

    override fun onRecipeItemClick(recipeId: Long) {
        recipesPresenter.displayRecipe(recipeId)
    }

    override fun showError(message: String) {
        Toast.makeText(context, getString(R.string.error_occurred_text) + message, Toast.LENGTH_SHORT).show()
    }
}
