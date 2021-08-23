package ru.androidlearning.recipessearch.presentation.fragments.recipe

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.androidlearning.recipessearch.presentation.RecipePresentationData

interface RecipeView : MvpView {

    @SingleState
    fun showRecipe(recipePresentationData: RecipePresentationData)
}
