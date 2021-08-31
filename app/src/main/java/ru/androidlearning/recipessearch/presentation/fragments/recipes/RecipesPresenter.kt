package ru.androidlearning.recipessearch.presentation.fragments.recipes

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.recipessearch.data.repository.RecipesRepository
import ru.androidlearning.recipessearch.navigation.RecipeFragmentScreen
import ru.androidlearning.recipessearch.navigation.SearchFragmentScreen
import ru.androidlearning.recipessearch.presentation.RecipesPresentationData
import ru.androidlearning.recipessearch.schedullers.WorkSchedulers
import javax.inject.Inject

class RecipesPresenter @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val schedulers: WorkSchedulers,
    private val router: Router
) : MvpPresenter<RecipesView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            recipesRepository.getRandomRecipes()
                .map(RecipesPresentationData.Mapper::map)
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    this::doOnSuccess,
                    this::doOnError
                )
    }

    private fun doOnSuccess(recipesPresentationData: RecipesPresentationData) {
        if (recipesPresentationData.recipes.isEmpty()) {
            viewState.showNoData()
        } else {
            viewState.showRecipes(recipesPresentationData)
        }
    }

    fun displayRecipe(recipeId: Long) {
        router.navigateTo(RecipeFragmentScreen(recipeId))
    }

    private fun doOnError(e: Throwable) {
        e.printStackTrace()
        e.message?.let { viewState.showError(it) }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun launchSearch() {
        router.navigateTo(SearchFragmentScreen())
    }
}
