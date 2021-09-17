package ru.androidlearning.recipessearch.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.observables.ConnectableObservable
import ru.androidlearning.recipessearch.data.network.NetworkState
import ru.androidlearning.recipessearch.data.network.NetworkStateObservable
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkStateObservableModule {
    @Singleton
    @Provides
    @Named("NetworkStateObservable")
    fun provideNetworkStateObservable(context: Context): ConnectableObservable<NetworkState> = NetworkStateObservable(context).publish()
}
