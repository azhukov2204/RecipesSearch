package ru.androidlearning.recipessearch.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.androidlearning.recipessearch.application.RecipesSearchApp
import ru.androidlearning.recipessearch.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        FragmentsModule::class,
        WorkSchedulersModule::class,
        RecipesApiModule::class,
        RepositoryModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<RecipesSearchApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        fun build(): ApplicationComponent
    }
}
