package ru.androidlearning.recipessearch.presentation.main_activity

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.observables.ConnectableObservable
import moxy.MvpPresenter
import ru.androidlearning.recipessearch.data.network.NetworkState
import ru.androidlearning.recipessearch.schedullers.WorkSchedulers
import javax.inject.Inject
import javax.inject.Named

class MainActivityPresenter @Inject constructor(
    @Named("NetworkStateObservable") private val networkStateConnectableObservable: ConnectableObservable<NetworkState>,
    private val schedulers: WorkSchedulers,
) : MvpPresenter<MainActivityView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposables +=
            networkStateConnectableObservable
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    this::showNetworkStateLabel,
                    this::doOnError
                )

        disposables +=
            networkStateConnectableObservable
                .connect()
    }

    private fun showNetworkStateLabel(networkState: NetworkState) {
        when (networkState) {
            NetworkState.DISCONNECTED -> viewState.showNetworkDisconnectLabel(true)
            NetworkState.CONNECTED -> viewState.showNetworkDisconnectLabel(false)
        }
    }

    private fun doOnError(e: Throwable) {
        e.printStackTrace()
        e.message?.let { viewState.showError(it) }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}
