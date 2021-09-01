package ru.androidlearning.recipessearch.presentation.fragments.search

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.recipessearch.data.repository.RecipesRepository
import ru.androidlearning.recipessearch.navigation.RecipeFragmentScreen
import ru.androidlearning.recipessearch.presentation.SearchResultPresentationData
import ru.androidlearning.recipessearch.schedullers.WorkSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val router: Router,
    private val recipesRepository: RecipesRepository,
    private val schedulers: WorkSchedulers,
) : MvpPresenter<SearchView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposables +=
            RxSearchObservable.getPublishSubject()
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .switchMapSingle { searchString -> recipesRepository.searchRecipesByName(searchString) }
                .map { searchResultsDTO -> searchResultsDTO.results.map(SearchResultPresentationData.Mapper::map) }
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    viewState::showSearchResults,
                    this::doOnError
                )
    }

    private fun doOnError(e: Throwable) {
        e.printStackTrace()
        e.message?.let { viewState.showError(it) }
    }

    fun comeBack() {
        router.exit()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun displayRecipe(recipeId: Long) {
        router.navigateTo(RecipeFragmentScreen(recipeId))
    }
}
