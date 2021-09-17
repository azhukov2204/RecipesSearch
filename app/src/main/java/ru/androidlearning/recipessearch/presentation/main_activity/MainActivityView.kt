package ru.androidlearning.recipessearch.presentation.main_activity

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.SingleState

interface MainActivityView : MvpView {
    @SingleState
    fun showNetworkDisconnectLabel(needShow: Boolean)

    @OneExecution
    fun showError(message: String)
}
