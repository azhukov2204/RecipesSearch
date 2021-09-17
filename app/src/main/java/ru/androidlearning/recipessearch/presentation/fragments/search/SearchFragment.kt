package ru.androidlearning.recipessearch.presentation.fragments.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.databinding.FragmentSearchBinding
import ru.androidlearning.recipessearch.presentation.SearchResultPresentationData
import ru.androidlearning.recipessearch.presentation.abstract_templates.DaggerMvpFragment
import ru.androidlearning.recipessearch.presentation.fragments.search.adapter.SearchResultsAdapter
import javax.inject.Inject

class SearchFragment : DaggerMvpFragment(R.layout.fragment_search), SearchView, SearchResultsAdapter.OnSearchResultClickListener {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val searchResultsAdapter = SearchResultsAdapter(this)

    @Inject
    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter(): SearchPresenter = searchPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            recipesSearchRecyclerView.adapter = searchResultsAdapter
            recipesSearchInputLayout.setStartIconOnClickListener { searchPresenter.comeBack() }
            RxSearchObservable.beginObserveEditTextChanges(recipesSearchTextInput)
        }
    }

    override fun onDestroyView() {
        RxSearchObservable.endObserveEditTextChanges(binding.recipesSearchTextInput)
        super.onDestroyView()
    }

    override fun showSearchResults(searchResults: List<SearchResultPresentationData>) {
        binding.recipesSearchRecyclerView.visibility = View.VISIBLE
        binding.noDataLabel.visibility = View.GONE
        searchResultsAdapter.submitList(searchResults)
    }

    override fun showNoData() {
        binding.recipesSearchRecyclerView.visibility = View.GONE
        binding.noDataLabel.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        Toast.makeText(context, getString(R.string.error_occurred_text) + message, Toast.LENGTH_SHORT).show()
    }

    override fun onSearchResultItemClick(recipeId: Long) {
        searchPresenter.displayRecipe(recipeId)
    }
}
