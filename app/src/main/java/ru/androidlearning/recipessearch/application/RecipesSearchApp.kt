package ru.androidlearning.recipessearch.application

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.androidlearning.recipessearch.di.DaggerApplicationComponent

class RecipesSearchApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out RecipesSearchApp> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext).apply {
                val cicerone: Cicerone<Router> = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .build()
}
