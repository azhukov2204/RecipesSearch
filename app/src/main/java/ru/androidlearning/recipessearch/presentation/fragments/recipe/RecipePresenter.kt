package ru.androidlearning.recipessearch.presentation.fragments.recipe

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import moxy.MvpPresenter
import ru.androidlearning.recipessearch.data.repository.RecipesRepository
import ru.androidlearning.recipessearch.presentation.RecipePresentationData
import ru.androidlearning.recipessearch.schedullers.WorkSchedulers

class RecipePresenter @AssistedInject constructor(
    @Assisted private val recipeId: Long?,
    private val recipesRepository: RecipesRepository,
    private val schedulers: WorkSchedulers
) : MvpPresenter<RecipeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        recipeId?.let { recipeId ->
            recipesRepository.getRecipeById(recipeId)
                .map(RecipePresentationData.Mapper::map)
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    viewState::showRecipe
                ) { it.printStackTrace() }  //todo доделать

        }
    }
}
