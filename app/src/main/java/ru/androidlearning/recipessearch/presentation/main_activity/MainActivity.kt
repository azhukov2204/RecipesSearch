package ru.androidlearning.recipessearch.presentation.main_activity

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.androidlearning.recipessearch.R
import ru.androidlearning.recipessearch.databinding.ActivityMainBinding
import ru.androidlearning.recipessearch.navigation.RecipesFragmentScreen
import ru.androidlearning.recipessearch.presentation.abstract_templates.DaggerMvpActivity
import javax.inject.Inject

class MainActivity : DaggerMvpActivity(R.layout.activity_main), MainActivityView {

    private val navigator = object : AppNavigator(this, R.id.container) {
        override fun setupFragmentTransaction(screen: FragmentScreen, fragmentTransaction: FragmentTransaction, currentFragment: Fragment?, nextFragment: Fragment) {
            fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN)
        }
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @InjectPresenter
    lateinit var mainActivityPresenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter = mainActivityPresenter

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

    override fun showNetworkDisconnectLabel(needShow: Boolean) {
        binding.noInternetLabel.isVisible = needShow
    }

    override fun showError(message: String) {
        Toast.makeText(this, getString(R.string.error_occurred_text) + message, Toast.LENGTH_SHORT).show()
    }
}
