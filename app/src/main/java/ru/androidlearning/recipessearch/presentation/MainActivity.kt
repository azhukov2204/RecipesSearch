package ru.androidlearning.recipessearch.presentation

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.navigation.RecipesFragmentScreen
import ru.androidlearning.recipessearch.presentation.abstract_templates.DaggerMvpActivity
import javax.inject.Inject

class MainActivity : DaggerMvpActivity(R.layout.activity_main) {

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(RecipesFragmentScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
