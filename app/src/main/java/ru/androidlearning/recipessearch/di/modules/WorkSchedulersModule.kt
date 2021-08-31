package ru.androidlearning.recipessearch.di.modules

import dagger.Module
import dagger.Provides
import ru.androidlearning.recipessearch.schedullers.WorkSchedulers
import ru.androidlearning.recipessearch.schedullers.WorkSchedulersImpl

@Module
class WorkSchedulersModule {

    @Provides
    fun provideWorkSchedulers(): WorkSchedulers = WorkSchedulersImpl()
}