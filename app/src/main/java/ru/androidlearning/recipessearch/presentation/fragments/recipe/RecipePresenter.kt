package ru.androidlearning.recipessearch.presentation.fragments.recipe

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.recipessearch.data.repository.RecipesRepository
import ru.androidlearning.recipessearch.presentation.RecipePresentationData
import ru.androidlearning.recipessearch.schedullers.WorkSchedulers

class RecipePresenter @AssistedInject constructor(
    @Assisted private val recipeId: Long?,
    private val recipesRepository: RecipesRepository,
    private val schedulers: WorkSchedulers,
    private val router: Router
) : MvpPresenter<RecipeView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        recipeId?.let { recipeId ->
            disposables +=
                recipesRepository.getRecipeById(recipeId)
                    .map(RecipePresentationData.Mapper::map)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .subscribe(
                        viewState::showRecipe,
                        this::doOnError
                    )
        }
    }

    private fun doOnError(e: Throwable) {
        e.printStackTrace()
        e.message?.let { viewState.showError(it) }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun closeFragment() {
        router.exit()
    }
}
