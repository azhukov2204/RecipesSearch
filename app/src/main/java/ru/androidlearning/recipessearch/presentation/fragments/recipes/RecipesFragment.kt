package ru.androidlearning.recipessearch.presentation.fragments.recipes

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search_item) {
            recipesPresenter.launchSearch()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showRecipes(recipesPresentationData: RecipesPresentationData) {
        binding.recipeRecyclerView.visibility = View.VISIBLE
        binding.noDataLabel.visibility = View.GONE
        binding.loadingSheet.root.visibility = View.GONE
        recipesAdapter.submitList(recipesPresentationData.recipes)
    }

    override fun showNoData() {
        binding.recipeRecyclerView.visibility = View.GONE
        binding.noDataLabel.visibility = View.VISIBLE
        binding.loadingSheet.root.visibility = View.GONE
    }

    override fun showError(message: String) {
        binding.loadingSheet.root.visibility = View.GONE
        Toast.makeText(context, getString(R.string.error_occurred_text) + message, Toast.LENGTH_SHORT).show()
    }

    override fun onRecipeItemClick(recipeId: Long) {
        recipesPresenter.displayRecipe(recipeId)
    }
}
