package ru.androidlearning.recipessearch.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.android.MainThreadDisposable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class NetworkStateObservable @Inject constructor(private val context: Context) : Observable<NetworkState>() {
    override fun subscribeActual(observer: Observer<in NetworkState>) {
        val listener = NetworkStateListener(context, observer)
        observer.onSubscribe(listener)
    }

    @Suppress("DEPRECATION")
    class NetworkStateListener(
        private val context: Context,
        private val observer: Observer<in NetworkState>
    ) : ConnectivityManager.NetworkCallback(), Disposable {

        private val availableNetworks = mutableSetOf<Network>()

        private val disposable = object : MainThreadDisposable() {
            override fun onDispose() {
                connectivityManager.unregisterNetworkCallback(this@NetworkStateListener)
            }
        }

        private val connectivityManager: ConnectivityManager by lazy {
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }

        init {
            connectivityManager.registerNetworkCallback(
                NetworkRequest.Builder()
                    .build(),
                this
            )
            val isConnected = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
            observer.onNext(
                if (isConnected) {
                    NetworkState.CONNECTED
                } else {
                    NetworkState.DISCONNECTED
                }
            )
        }

        override fun dispose() = disposable.dispose()

        override fun isDisposed(): Boolean = disposable.isDisposed

        override fun onAvailable(network: Network) {
            availableNetworks.add(network)
            if (availableNetworks.isNotEmpty()) {
                observer.onNext(NetworkState.CONNECTED)
            }

        }

        override fun onLost(network: Network) {
            availableNetworks.remove(network)
            if (availableNetworks.isEmpty()) {
                observer.onNext(NetworkState.DISCONNECTED)
            }
        }
    }
}
