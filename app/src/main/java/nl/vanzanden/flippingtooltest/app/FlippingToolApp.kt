package nl.vanzanden.flippingtooltest.app

import android.app.Application
import nl.vanzanden.flippingtooltest.di.AppComponent
import nl.vanzanden.flippingtooltest.di.AppModule
import nl.vanzanden.flippingtooltest.di.DaggerAppComponent
import nl.vanzanden.flippingtooltest.di.logistic.DaggerLogisticComponent
import nl.vanzanden.flippingtooltest.di.logistic.LogisticComponent

/**
 * Created by alan on 03/11/2020.
 */
class FlippingToolApp : Application() {

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        logis = DaggerLogisticComponent.builder().appComponent(graph).build()
    }

    companion object {
        lateinit var graph: AppComponent
        lateinit var logis: LogisticComponent
    }

}