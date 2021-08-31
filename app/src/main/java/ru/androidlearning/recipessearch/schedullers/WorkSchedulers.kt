package ru.androidlearning.recipessearch.schedullers

import io.reactivex.rxjava3.core.Scheduler

interface WorkSchedulers {
    fun threadIO(): Scheduler
    fun threadMain(): Scheduler
}
