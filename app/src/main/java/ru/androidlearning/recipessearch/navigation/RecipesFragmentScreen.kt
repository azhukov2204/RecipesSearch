package ru.androidlearning.recipessearch.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.recipessearch.presentation.fragments.recipes.RecipesFragment

class RecipesFragmentScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        RecipesFragment.newInstance()

}
