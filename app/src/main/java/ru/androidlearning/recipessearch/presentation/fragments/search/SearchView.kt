package ru.androidlearning.recipessearch.presentation.fragments.search

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.SingleState
import ru.androidlearning.recipessearch.presentation.SearchResultPresentationData

interface SearchView : MvpView {

    @SingleState
    fun showSearchResults(searchResults: List<SearchResultPresentationData>)

    @OneExecution
    fun showError(message: String)
}
