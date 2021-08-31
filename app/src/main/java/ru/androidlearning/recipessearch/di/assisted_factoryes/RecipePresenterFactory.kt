package ru.androidlearning.recipessearch.di.assisted_factoryes

import dagger.assisted.AssistedFactory
import ru.androidlearning.recipessearch.presentation.fragments.recipe.RecipePresenter

@AssistedFactory
interface RecipePresenterFactory {
    fun create(recipeId: Long?): RecipePresenter
}
