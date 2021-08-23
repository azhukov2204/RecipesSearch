package ru.androidlearning.recipessearch.presentation.fragments.recipes

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.androidlearning.recipessearch.data.repository.RecipesRepository
import ru.androidlearning.recipessearch.navigation.RecipeFragmentScreen
import ru.androidlearning.recipessearch.presentation.RecipesPresentationData
import ru.androidlearning.recipessearch.schedullers.WorkSchedulers
import javax.inject.Inject

class RecipesPresenter @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val schedulers: WorkSchedulers,
    private val router: Router
) : MvpPresenter<RecipesView>() {
    override fun onFirstViewAttach() {
        recipesRepository.getRandomRecipes()
            .map(RecipesPresentationData.Mapper::map)
            .observeOn(schedulers.threadMain())
            .subscribeOn(schedulers.threadIO())
            .subscribe(
                viewState::showRecipes,
                { it.printStackTrace() } //todo доделать
            )
    }

    fun displayRecipe(recipeId: Long) {
        router.navigateTo(RecipeFragmentScreen(recipeId))
    }
}
