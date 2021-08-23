package ru.androidlearning.recipessearch.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.recipessearch.presentation.fragments.recipe.RecipeFragment

class RecipeFragmentScreen(private val recipeId: Long) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        RecipeFragment.newInstance(recipeId)
}
