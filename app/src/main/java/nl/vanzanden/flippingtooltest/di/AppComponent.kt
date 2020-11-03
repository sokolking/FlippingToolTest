package nl.vanzanden.flippingtooltest.di

import android.content.Context
import dagger.Component
import nl.vanzanden.flippingtooltest.data.repository.impl.Repository
import nl.vanzanden.flippingtooltest.data.repository.preferences.PreferenceHelper
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun context(): Context
    fun repository(): Repository
    fun preferenceHelper(): PreferenceHelper
}