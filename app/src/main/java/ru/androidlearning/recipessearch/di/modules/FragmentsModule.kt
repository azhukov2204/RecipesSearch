package ru.androidlearning.recipessearch.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.androidlearning.recipessearch.presentation.MainActivity
import ru.androidlearning.recipessearch.presentation.fragments.recipe.RecipeFragment
import ru.androidlearning.recipessearch.presentation.fragments.recipes.RecipesFragment
import ru.androidlearning.recipessearch.presentation.fragments.search.SearchFragment

@Module
interface FragmentsModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindRecipesFragment(): RecipesFragment

    @ContributesAndroidInjector
    fun bindRecipeFragment(): RecipeFragment

    @ContributesAndroidInjector
    fun bindSearchFragment(): SearchFragment
}
