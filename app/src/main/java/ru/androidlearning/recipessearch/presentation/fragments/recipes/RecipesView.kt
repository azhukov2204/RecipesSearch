package ru.androidlearning.recipessearch.presentation.fragments.recipes

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.SingleState
import ru.androidlearning.recipessearch.presentation.RecipesPresentationData

interface RecipesView: MvpView {

    @SingleState
    fun showRecipes(recipesPresentationData: RecipesPresentationData)

    @OneExecution
    fun showError(message: String)
}
