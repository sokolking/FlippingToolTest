package nl.vanzanden.flippingtooltest.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.newThread
import nl.vanzanden.flippingtooltest.util.retrofit.RetryWithDelay

/**
 * Created by alan on 03/11/2020.
 */

fun <T : Any> Observable<T>?.defaultObservable(): Observable<T> {
    return this
        ?.subscribeOn(newThread())
        ?.observeOn(mainThread())
        ?.retryWhen(object : RetryWithDelay(3, 3000L) {})!!
}